package app.service.impl;

import app.FruitOperationStrategy;
import app.service.FileReadService;
import app.service.Operation;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OperationReturnTest {
    private static Map<String, Operation> fruitOperations;
    public static final String FIRST_FILE = "C:\\Users\\38093\\Desktop\\Mate academy"
            + "\\jv-fruit-shop_prod\\src\\test\\java\\resources\\testCantBuy3.csv";
    public static final String SECOND_FILE = "C:\\Users\\38093\\Desktop\\Mate academy" +
            "\\jv-fruit-shop_prod\\src\\test\\java\\resources\\testCantBuy2.csv";
    private static FruitOperationStrategy fruitOperationStrategy;
    private static FileReadService fileReadService;

    @BeforeClass
    public static void start() {
        fruitOperations = new HashMap<>();
        fruitOperations.put("s",new OperationSupply());
        fruitOperations.put("b", new OperationBuy());
        fruitOperations.put("r", new OperationReturn());
        fruitOperationStrategy = new FruitOperationStrategy(fruitOperations);
        fileReadService = new FileReadServiceImplements();
    }

    @Test(expected = RuntimeException.class)
    public void nonExistentReturn() {
        List<List<String>> allData = fileReadService.readFile(FIRST_FILE);
        for (List<String> line : allData) {
            Operation operation = fruitOperationStrategy.getOperation(line);
            operation.doOperation(line);
        }
    }
}