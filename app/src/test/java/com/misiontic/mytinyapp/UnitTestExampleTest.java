package com.misiontic.mytinyapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnitTestExampleTest {

    private UnitTestExample test;

    @Before
    public void setUp() throws Exception {
        test = new UnitTestExample();
    }

    @Test
    public void validPassword() throws Exception{
        assertEquals(true,test.validPassword("123456789000"));
    }

    @Test
    public void validInput() throws Exception{
        assertEquals(false,test.validInput("") );
    }
}