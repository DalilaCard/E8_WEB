package com.upiiz.E08.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigaCustomResponse<T> {

    private int estado;
    private String msg;
    private T ligas;
    private List<Link> links;

}
