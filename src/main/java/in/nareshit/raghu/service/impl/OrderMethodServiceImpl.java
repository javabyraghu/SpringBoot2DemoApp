package in.nareshit.raghu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nareshit.raghu.exception.OrderNotFoundException;
import in.nareshit.raghu.model.OrderMethod;
import in.nareshit.raghu.repo.OrderMethodRepository;
import in.nareshit.raghu.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository repo;

	@Override
	@Transactional
	public Integer saveOrderMethod(OrderMethod om) {
		return repo.save(om).getId();
	}

	@Override
	@Transactional
	public void updateOrderMethod(OrderMethod om) {
		Optional<OrderMethod> opt =  repo.findById(om.getId());
		if(opt.isPresent()) 
			repo.save(om);
		else
			throw new OrderNotFoundException("Order Method '"+om.getId()+"' Not Found");
	}

	@Override
	@Transactional
	public void deleteOrderMethod(Integer id) {
		Optional<OrderMethod> opt =  repo.findById(id);
		if(opt.isPresent()) 
			repo.delete(opt.get());
		else
			throw new OrderNotFoundException("Order Method '"+id+"' Not Found");
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<OrderMethod> getOneOrderMethod(Integer id) {
		Optional<OrderMethod> opt =  repo.findById(id);
		if(opt.isPresent()) 
			return opt;
		else
			throw new OrderNotFoundException("Order Method '"+id+"' Not Found");
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrderMethod> getAllOrderMethods() {
		return repo.findAll();
	}


}
