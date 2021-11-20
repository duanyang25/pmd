package net.sourceforge.pmd;

import com.beust.jcommander.JCommander;
import net.sourceforge.pmd.cli.PMDCommandLineInterface;
import net.sourceforge.pmd.cli.PMDParameters;
import net.sourceforge.pmd.cli.PmdParametersParseResult;
import net.sourceforge.pmd.cpd.CPDCommandLineInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.RestoreSystemProperties;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.RulePriority;
import net.sourceforge.pmd.annotation.InternalApi;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.LanguageVersion;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.validators.PositiveInteger;
import net.sourceforge.pmd.PMD;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.lang.DummyLanguageModule;
import net.sourceforge.pmd.lang.Language;
import net.sourceforge.pmd.util.datasource.DataSource;
import static net.sourceforge.pmd.PMD.runPmd;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import net.sourceforge.pmd.PMD;

import java.util.Map;

/**
 * Unit test for CS427
 *
 */
public class cs427Test {

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
        String[] args = { "--help" };
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

        Assert.assertEquals(true,arguments.isVersion());
    }

    /**
     * Unit test for CS427
     * Test for PmdParametersParseResult function
     */
    @Test
    public void testPmdParametersParseResultNormal() {
//        Constructors of PmdParametersParseResult are not public, ignore this,
//        Go to the end of the file: pmd-core/src/test/java/net/sourceforge/pmd/cli/PMDCommandLineInterfaceTest.java


//        PMDParameters arguments = new PMDParameters();
//        String[] args = { "--version" };
//
//        JCommander jcommander = new JCommander(arguments);
//        jcommander.setProgramName("PMD");
//        jcommander.parse(args);
//        PmdParametersParseResult result = new PmdParametersParseResult(arguments, null);
//
//
//        Assert.assertEquals(true,arguments.isVersion());
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


//        PMDParameters arguments = new PMDParameters();
//        String[] args = { "-help" };
//
//        JCommander jcommander = new JCommander(arguments);
//        jcommander.setProgramName("PMD");
//        jcommander.parse(args);
//        PmdParametersParseResult result = PmdParametersParseResult.filterDeprecatedOptions(args);
//
//        Assert.assertEquals(false,result.toConfiguration() == null);
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
