package src;

import java.util.List;
import java.util.Map;

class LoxClass extends LoxInstance implements LoxCallable {
    final String name;
    private final Map<String, LoxFunction> methods;
    private final Map<String, LoxFunction> staticMethods;

    LoxClass(String name, Map<String, LoxFunction> methods, Map<String, LoxFunction> staticMethods) {
        this.name = name;
        this.methods = methods;
        this.staticMethods = staticMethods;
        this.klass = this;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter,
                       List<Object> arguments) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public int arity() {
        LoxFunction initializer = findMethod("init");
        if (initializer == null) return 0;
        return initializer.arity();
    }
    LoxFunction findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }
        if (staticMethods.containsKey(name)){
            return staticMethods.get(name);
        }
        return null;
    }
}
