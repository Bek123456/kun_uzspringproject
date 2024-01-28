package org.example.springkunuz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class PaginationResultDTO<T> {
    private Long totalSize;
    private List<T> profileList;

    public PaginationResultDTO(Long totalSize, List<T> profileList) {
        this.totalSize = totalSize;
        this.profileList = profileList;
    }
}
