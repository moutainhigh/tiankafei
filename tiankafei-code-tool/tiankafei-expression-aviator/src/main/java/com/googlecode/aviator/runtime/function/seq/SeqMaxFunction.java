package com.googlecode.aviator.runtime.function.seq;

/**
 * seq.max function to find the largest element in sequence.
 *
 * @author dennis
 * @since 4.1.2
 */
public class SeqMaxFunction extends AbstractSeqMinMaxFunction {

    @Override
    public String getName() {
        return "seq.max";
    }

    @Override
    protected Op getOp() {
        return Op.Max;
    }
}
