/**
 * 
 */
package gdc.item.datamanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gdc.item.datamanager.pojo.Item;

/**
 * @author suhada
 *
 */
@Repository
@Transactional(readOnly = true)
public class ItemCustomRepositoryImpl implements ItemCustomRepository{

private static org.slf4j.Logger logger = LoggerFactory.getLogger(ItemCustomRepositoryImpl.class);
	
	@PersistenceContext
    EntityManager entityManager;
	
	@Override
	public HashMap<String, Object> findItems(HashMap<String, Object> param) {
		logger.debug("------>> start findItems() <<------");
		try {
			int page = -1, count = -1;
			String name="",code="",barcode="",status="";
			
			if(param.get("page") != null) {
				page = (int)param.get("page");
			}
			if(param.get("count") != null) {
				count = (int)param.get("count");
			}
			if(param.get("name") != null) {
				name = (String)param.get("name");
			}
			if(param.get("code") != null) {
				code = (String)param.get("code");
			}
			if(param.get("status") != null) {
				status = (String)param.get("status");
			}
			if(param.get("barcode") != null) {
				barcode = (String)param.get("barcode");
			}
			
			logger.debug("------>> params count:"+count+", page:"+page+", name:"+name+", code:"+code+", barcode:"+barcode+", status:"+status+"");
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Item> itemSelectQuery = criteriaBuilder.createQuery(Item.class);
			Root<Item> root = itemSelectQuery.from(Item.class);
			itemSelectQuery.select(root);
			
			List<Predicate> predicates = new ArrayList<>();
			if(name != null && !name.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+name+"%"));
			}
			if(code != null && !code.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("code").as(String.class), "%"+code+"%"));
			}
			if(barcode != null && !barcode.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("barcode").as(String.class), "%"+barcode+"%"));
			}
			if(status != null && !status.equals("") && !status.equalsIgnoreCase("ALL")) {
				predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), status));
			}
			
			itemSelectQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			
			TypedQuery<Item> final_uery = this.entityManager.createQuery(itemSelectQuery);
			if(count>-1 && page >-1) {
				final_uery.setFirstResult((page-1)*count);
			}
			if(count>-1) {
				final_uery.setMaxResults(count);
			}
			List<Item> list = final_uery.getResultList();
			logger.debug("------>> list:"+list);
			param.put("list", list);
			
			CriteriaQuery<Long> itemCountQuery = criteriaBuilder.createQuery(Long.class);
			itemCountQuery.select(criteriaBuilder.count(itemCountQuery.from(Item.class)));
			Long total_count = this.entityManager.createQuery(itemCountQuery).getSingleResult();
			logger.debug("------>> total_count:"+total_count);
			param.put("total_count", total_count);
			
			int page_count = 0;
			page_count = (int) (total_count/count);
			if(total_count%count>0) {
				page_count+=1;
			}
			logger.debug("------>> page_count:"+page_count);
			param.put("page_count", page_count);
			
		} catch (Exception e) {
			logger.error("------>> Error :",e);
		}
		logger.debug("------>> end findItems() <<------");
		return param;
	}

	@Override
	public Long findItemCount(HashMap<String, Object> param) {
		logger.debug("------>> start findItemCount() <<------");
		Long count = new Long(0);
		try {
			String name="",code="",barcode="",status="";
			
			if(param.get("name") != null) {
				name = (String)param.get("name");
			}
			if(param.get("code") != null) {
				code = (String)param.get("code");
			}
			if(param.get("status") != null) {
				status = (String)param.get("status");
			}
			if(param.get("barcode") != null) {
				barcode = (String)param.get("barcode");
			}
			
			logger.debug("------>> params name:"+name+", code:"+code+", barcode:"+barcode+", status:"+status+"");
			
			CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
			CriteriaQuery<Long> itemCountQuery = criteriaBuilder.createQuery(Long.class);
			
			Root<Item> root = itemCountQuery.from(Item.class);
			itemCountQuery.select(criteriaBuilder.count(root));
			
			List<Predicate> predicates = new ArrayList<>();
			if(name != null && !name.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+name+"%"));
			}
			if(code != null && !code.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("code").as(String.class), "%"+code+"%"));
			}
			if(barcode != null && !barcode.equals("")) {
				predicates.add(criteriaBuilder.like(root.get("barcode").as(String.class), "%"+barcode+"%"));
			}
			if(status != null && !status.equals("") && !status.equalsIgnoreCase("ALL")) {
				predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), status));
			}
			
			itemCountQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
			
			count = this.entityManager.createQuery(itemCountQuery).getSingleResult();
			
		}catch(Exception e) {
			logger.error("------>> Error in getting count :",e);
		}
		logger.debug("------>> end findItemCount() <<------");
		return count;
	}

}
