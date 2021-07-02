package raf.nwp.aircompany.dtos;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T>(List<T> content, int totalPages, int currentPage, long totalElements, int maxElementsPerPage) {

    public static <T> PageResponse<T> of(Page<T> page) {
        return new PageResponse<>(page.getContent(), page.getTotalPages(), page.getNumber(), page.getTotalElements(), page.getSize());
    }
}
