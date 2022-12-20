package course_paper;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.complex.Complex;

@RequiredArgsConstructor
public class SeriesCalculator {

    private final Function function;

    public Complex getSeriesValue(int fromN, int toN) {
        Complex result = Complex.ZERO;
        for (int n = fromN; n < toN + 1; n++) {
            result.add(function.getValue(n));
        }
        return result;
    }

}
