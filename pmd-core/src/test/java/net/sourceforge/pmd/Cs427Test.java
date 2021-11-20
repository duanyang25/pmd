/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd;

import static net.sourceforge.pmd.PMD.runPmd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;

import net.sourceforge.pmd.cli.PMDCommandLineInterface;
import net.sourceforge.pmd.cli.PMDParameters;
import net.sourceforge.pmd.cli.PmdParametersParseResult;
import net.sourceforge.pmd.cpd.CPDCommandLineInterface;

import com.beust.jcommander.JCommander;



/**
 * Unit test for CS427
 *
 */
public class Cs427Test {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Rule // Restores system properties after test
    public final RestoreSystemProperties restoreSystemProperties = new RestoreSystemProperties();

    @Before
    public void clearSystemProperties() {
        System.clearProperty(PMDCommandLineInterface.NO_EXIT_AFTER_RUN);
        System.clearProperty(PMDCommandLineInterface.STATUS_CODE_PROPERTY);
    }


    /**
     * Unit test for CS427
     * Test for RunPMD function
     */
    @Test
    public void testRunPMD() {
        String[] args = { "-h" };
        PMD.StatusCode exitCode = runPmd(args);
        Assert.assertEquals(PMD.StatusCode.OK, exitCode);
    }

    /**
     * Unit test for CS427
     * Test for RunPMD function
     */
    @Test
    public void testRunPMDHelp() {
        String[] args = { "--help", };
        PMD.StatusCode exitCode = runPmd(args);
        Assert.assertEquals(PMD.StatusCode.OK, exitCode);
    }

    /**
     * Unit test for CS427
     * Test for RunPMD function
     */
    @Test
    public void testRunPMDVersion() {
        String[] args = { "--version", };
        PMD.StatusCode exitCode = runPmd(args);
        Assert.assertEquals(PMD.StatusCode.OK, exitCode);
    }

    /**
     * Unit test for CS427
     * Test for RunPMD function
     */
    @Test
    public void testRunPMDError() {
        String[] args = { "-d" };
        PMD.StatusCode exitCode = runPmd(args);
        Assert.assertEquals(PMD.StatusCode.ERROR, exitCode);
    }

    /**
     * Unit test for CS427
     * Test for IsVersion function
     */
    @Test
    public void testIsVersion() {
        PMDParameters arguments = new PMDParameters();
        String[] args = { "--version" };

        JCommander jcommander = new JCommander(arguments);
        jcommander.setProgramName("PMD");
        jcommander.parse(args);

        Assert.assertEquals(true, arguments.isVersion());
    }

    /**
     * Unit test for CS427
     * Test for PmdParametersParseResult function
     */
    @Test
    public void testPmdParametersParseResultNormal() {
        // Constructors of PmdParametersParseResult are not public, ignore this,
        // Go to the end of the file: pmd-core/src/test/java/net/sourceforge/pmd/cli/PMDCommandLineInterfaceTest.java

        // PMDParameters arguments = new PMDParameters();
        // String[] args = { "--version" };

        // JCommander jcommander = new JCommander(arguments);
        // jcommander.setProgramName("PMD");
        // jcommander.parse(args);
        // PmdParametersParseResult result = new PmdParametersParseResult(arguments, null);

        // Assert.assertEquals(true,arguments.isVersion());
    }


    /**
     * Unit test for CS427
     * Test for ExtractParameter function
     */
    @Test
    public void testExtractParameters() {
        PMDParameters arguments = new PMDParameters();
        String[] args = {"--version"};

        JCommander jcommander = new JCommander(arguments);
        jcommander.setProgramName("PMD");
        jcommander.parse(args);
        PmdParametersParseResult result = PmdParametersParseResult.extractParameters(args);

        Assert.assertEquals(null, result.toConfiguration());
    }

    /**
     * Unit test for CS427
     * Test for FilterDeprecatedOptions function
     */
    @Test
    public void testFilterDeprecatedOptions() {
        // filterDeprecatedOptions is private function, ignore this


        // PMDParameters arguments = new PMDParameters();
        // String[] args = { "-help" };

        // JCommander jcommander = new JCommander(arguments);
        //  jcommander.setProgramName("PMD");
        // jcommander.parse(args);
        // PmdParametersParseResult result = PmdParametersParseResult.filterDeprecatedOptions(args);

        // Assert.assertEquals(false,result.toConfiguration() == null);
    }

    protected int getStatusCode() {
        return Integer.parseInt(System.getProperty(PMDCommandLineInterface.STATUS_CODE_PROPERTY));
    }

    /**
     * Unit test for CS427
     * Test for CPDCommandLineInterface.Main function
     */
    @Test
    public void testMain() {
        PMDParameters params = new PMDParameters();
        String[] args = { "--help" };

        exit.expectSystemExit();
        CPDCommandLineInterface.main(args);
        Assert.assertEquals(PMD.StatusCode.OK, getStatusCode());
    }

}
