package tv.codely.shared.domain.criteria;

import java.util.HashMap;

public final class Filter {
    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValienv value;

    public Filter(FilterField field, FilterOperator operator, FilterValienv value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public static Filter FromValues(HashMap<String, String> values) {
        return new Filter(
            new FilterField(values.get("field")),
            FilterOperator.fromValue(values.get("operator")),
            new FilterValue(values.get("value"))
        );
    }

     public FilterField field() {
        return field;
    }

    public FilterOperator operator() {
        return operator;
    }

    public FilterValue value() {
        return value;
    }
}
