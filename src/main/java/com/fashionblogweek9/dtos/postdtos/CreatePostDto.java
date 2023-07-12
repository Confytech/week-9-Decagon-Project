package com.fashionblogweek9.dtos.postdtos;

import com.fashionblogweek9.enums.DesignType;
import com.fashionblogweek9.enums.DesignTypeGender;
import lombok.Data;

@Data
public class CreatePostDto {
    private String postTitle;
    private String postDescription;
    private DesignType designType;
    private DesignTypeGender designTypeGender;
}
