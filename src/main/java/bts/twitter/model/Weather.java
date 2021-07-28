package bts.twitter.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {
    private String icon;
    private String code;
    private String description;
}
