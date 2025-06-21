package com.java.business.HeadOffice.repository;

import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.java.business.HeadOffice.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

	@Query(value="select c.category_id, c.category, p.productid, p.productname, p.hsn_code, p.unit_id,p.stock, u.unitname,p.tax_percentage from category as c"
			+ " left join product as p on c.category_id = p.categoryid"
			+ " left join unit as u on p.unit_id = u.unit_id", nativeQuery = true)
	List<Map<String, Object>> getAllProductsByCategory();
	
	@Query(value="select c.category_id, c.category, p.productid, p.productname, p.hsn_code, p.unit_id,p.stock,p.tax_percentage from category as c"
			+ " left join product as p on c.category_id = p.categoryid"
			, nativeQuery = true)
	List<Map<String, Object>> getAllProductsByCategory1();
	
	 @Query(value = "select * from category c order by c.category_id desc", nativeQuery = true)
	 Iterable<Category> findAllByOrderByDesc();
	
	 
	List<Category> findByCategory(String category);
	
//	List<Category> findByCategory1(Category category);
}
