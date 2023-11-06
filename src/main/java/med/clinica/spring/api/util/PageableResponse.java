package med.clinica.spring.api.util;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageableResponse<T> {
    public int page ;
    public int limit;
    public long totalItems;
    public List<T> list = new ArrayList<>();
    public PageableResponse(List<T> objType, String offset, String limit) {
        this.page = Integer.parseInt(offset);
        this.limit = Integer.parseInt(limit);
        this.totalItems = objType.size();
        this.list = objType.subList(page * this.limit, (page * this.limit) + this.limit);
    }
    public PageableResponse(List<T> objType) {
        this.page = 0;
        this.totalItems = objType.size();
        this.list = objType;
    }

    public PageableResponse(Page<T> page){  this.limit = page.getSize();
        this.list = page.getContent();
        this.page = page.getNumber() + 1;
        this.totalItems = page.getTotalElements();

    }


}
