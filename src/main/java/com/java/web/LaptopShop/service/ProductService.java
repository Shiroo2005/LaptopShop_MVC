package com.java.web.LaptopShop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.java.web.LaptopShop.domain.Cart;
import com.java.web.LaptopShop.domain.CartDetail;
import com.java.web.LaptopShop.domain.Order;
import com.java.web.LaptopShop.domain.OrderDetail;
import com.java.web.LaptopShop.domain.Product;
import com.java.web.LaptopShop.domain.Product_;
import com.java.web.LaptopShop.domain.User;
import com.java.web.LaptopShop.domain.DTO.FilterProductDTO;
import com.java.web.LaptopShop.repository.CartDetailRepository;
import com.java.web.LaptopShop.repository.CartRepository;
import com.java.web.LaptopShop.repository.OrderDetailReposiroty;
import com.java.web.LaptopShop.repository.OrderRepository;
import com.java.web.LaptopShop.repository.ProductRepositoy;
import com.java.web.LaptopShop.repository.UserRepository;
import com.java.web.LaptopShop.service.Specification.ProductSpec;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepositoy productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailReposiroty orderDetailReposiroty;

    public ProductService(ProductRepositoy productRepository, UserRepository userRepository,
            CartRepository cartRepository, CartDetailRepository cartDetailRepository, OrderRepository orderRepository,
            OrderDetailReposiroty orderDetailReposiroty) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.orderRepository = orderRepository;
        this.orderDetailReposiroty = orderDetailReposiroty;
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public long countProduct() {
        return this.productRepository.count();
    }

    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Page<Product> findAll(Pageable pageable, FilterProductDTO filterProductDTO) {
        Specification<Product> proSpec = Specification.where(null);
        if (filterProductDTO.getFactory() != null && filterProductDTO.getFactory().isPresent()) {
            proSpec = proSpec.and(ProductSpec.factoryIn(filterProductDTO.getFactory().get()));
        }

        if (filterProductDTO.getTarget() != null && filterProductDTO.getTarget().isPresent()) {
            proSpec = proSpec.and(ProductSpec.targetIn(filterProductDTO.getTarget().get()));
        }

        if (filterProductDTO.getPrice() != null && filterProductDTO.getPrice().isPresent()) {
            proSpec = proSpec.and(ProductSpec.priceIn(filterProductDTO.getPrice().get()));
        }

        if (filterProductDTO.getSort() != null && filterProductDTO.getSort().isPresent()) {
            String sort = filterProductDTO.getSort().get();
            if (sort.equals("gia-giam-dan")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.by(Product_.PRICE).descending());
            } else if (sort.equals("gia-tang-dan")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.by(Product_.PRICE).ascending());
            }
        }

        return this.productRepository.findAll(proSpec, pageable);
    }

    public Product findById(long id) {
        return this.productRepository.findById(id).get();
    }

    public void handleAddProductToCart(HttpSession session, long productId, long quantity) {
        Product product = this.productRepository.findById(productId).get();

        User user = this.userRepository.findById((long) session.getAttribute("id")).get();
        Cart cart = this.cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setSum(0);
            this.cartRepository.save(cart);
        }

        CartDetail cartDetail = this.cartDetailRepository.findByProductAndCart(product, cart);
        if (cartDetail == null) {

            cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(0);
            cart.setSum(cart.getSum() + 1);
            cartDetail.setPrice(product.getPrice());
            session.setAttribute("sum", cart.getSum());
        }

        cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
        this.cartDetailRepository.save(cartDetail);

    }

    public void handleOrder(HttpSession session, User user) {
        Cart cart = user.getCart();
        if (cart != null) {
            Order order = new Order();
            order.setStatus("PENDING");
            order.setUser(cart.getUser());

            order = this.orderRepository.save(order);
            long totalPrice = 0;
            for (CartDetail cartDetail : cart.getCartDetails()) {
                OrderDetail orderDetail = CartDetailService.toOrderDetail(cartDetail);
                orderDetail.setOrder(order);
                this.orderDetailReposiroty.save(orderDetail);
                totalPrice = cartDetail.getPrice() * cartDetail.getQuantity();
            }

            order.setTotalPrice(totalPrice);

            // delete cart
            this.cartDetailRepository.deleteByCart(cart);
            this.cartRepository.deleteById(cart.getId());
        }

        // update session
        session.setAttribute("sum", 0);
    }

    public void deleteById(long id) {
        this.productRepository.deleteById(id);
    }

    public void deleteByList(List<Long> list) {
        if (list.size() != 1) {
            if (list.contains(0L))
                this.productRepository.deleteAll();
            else
                this.productRepository.deleteByIdIn(list);
        }
    }
}
