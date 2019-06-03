package fr.excilys.webapp.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageableMapper {

	public Pageable getPageable(Page page, PageQuery query) {
		System.out.println(query.getOrder() + " " + query.getSens());
		if (query.getSens().equals("ASC"))
			return PageRequest.of(page.getCurrent() - 1, page.getLimit(), Sort.by(query.getOrder()).ascending());
		return PageRequest.of(page.getCurrent() - 1, page.getLimit(), Sort.by(query.getOrder()).descending());
	}
}
