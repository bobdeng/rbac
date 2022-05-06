package cn.bobdeng.base.role;

import com.google.gson.Gson;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class RoleFunctions {
    @Getter
    private List<String> functions;

    public RoleFunctions(List<String> functions) {

        this.functions = functions;
    }

    public RoleFunctions(String functions) {
        this.functions = Arrays.asList(new Gson().fromJson(functions, String[].class));
    }

    public boolean contains(String functionName) {
        return this.functions.contains(functionName);
    }
}
