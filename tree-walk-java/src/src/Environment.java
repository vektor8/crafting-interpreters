package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();
    private final List<Object> vals = new ArrayList<>();
    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    Environment(){
        enclosing = null;
    }

    void define(String name, Object value) {
        values.put(name, value);
        vals.add(value);
    }

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }
        if (enclosing != null) return enclosing.get(name);
        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.");
    }
    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }
        throw new RuntimeError(name,
                "Undefined variable '" + name.lexeme + "'.");
    }

    Object getAt(int distance, int index) {
        return ancestor(distance).vals.get(index);
    }

    Environment ancestor(int distance) {
        Environment environment = this;
        for (int i = 0; i < distance; i++) {
            environment = environment.enclosing;
        }

        return environment;
    }

    void assignAt(int distance, int index, Object value) {
//        ancestor(distance).values.put(name.lexeme, value);
        var env = ancestor(distance);
        if (index >= env.vals.size()){
            assert(index == env.vals.size());
            env.vals.add(value);
            return;
        }
        env.vals.set(index, value);
    }
}
