package by.rudenko.imarket;

/**
 * Check Pagination parameters for correction output
 *
 */
public class CheckPagination {
    private int pageNumber;
    private Integer pageSize;
    private Long count;
    private int defaultPageSize;

    public CheckPagination(int pageNumber, Integer pageSize, Long count, int defaultPageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.count = count;
        this.defaultPageSize = defaultPageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public CheckPagination check() {
        //проверка на корректность параметров пагинации
        if (pageSize == null) {
            pageSize = defaultPageSize;
        }
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }
        if (pageNumber * pageSize > count) {
            pageSize = defaultPageSize;
            pageNumber = (int) (count / pageSize);
        }
        return this;
    }
}
