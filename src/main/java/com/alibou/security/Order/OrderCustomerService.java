package com.alibou.security.Order;
import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class OrderCustomerService {

    private final OrderCustomerRepository orderCustomerRepository;
    private final UserRepository userRepository;
    @Transactional
    public List<OrderCustomer> findAll() {
        return orderCustomerRepository.findAll();
    }


    public Optional<OrderCustomer> findById(Long id) {
        return orderCustomerRepository.findById(id);
    }

    public OrderCustomer save(OrderCustomer orderCustomer) {
        return orderCustomerRepository.save(orderCustomer);
    }

    public void deleteById(Long id) {
        orderCustomerRepository.deleteById(id);
    }

    /*
    public OrderCustomer findExistingOrder(Long userId) {
        User user = findUserById(userId);
        return orderCustomerRepository.findByUserAndStatus(user, false);
    }*/

    public OrderCustomer saveOrder(OrderCustomer orderCustomer) {
        return orderCustomerRepository.save(orderCustomer);
    }


}