import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class Test1
{
    public static void main(String[] argv)
    {
	Stream<Double> randoms = Stream.generate(Math::random).limit(100);
	randoms.forEach(e -> System.out.println(e));
    }
}

