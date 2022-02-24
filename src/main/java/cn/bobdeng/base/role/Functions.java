package cn.bobdeng.base.role;

import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode
public class Functions {
    private List<Function> functionList;

    public Functions(List<Function> functionList) {

        this.functionList = functionList;
    }

    public static Functions fromJson(String functions) {
        return new Functions(Arrays.asList(new Gson().fromJson(functions, Function[].class)));
    }

    public String asJson() {
        return new Gson().toJson(functionList);
    }

    public boolean hasPermission(Function function) {
        return this.functionList.contains(function);
    }
}
