package com.paypay.oa.order.controller.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOptions {
    List<OptionGroup> optionGroup;
    private Integer totalPrice;

    @Data
    public static class OptionGroup {
        private Long groupId;
        private String name;
        private List<Options> options;

        @Data
        public static class Options {
            private Long optionStoreMapId;
            private Long optionId;
            private String name;
            private Integer price;
        }
    }
}
