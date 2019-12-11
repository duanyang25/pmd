/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.plsql;

import net.sourceforge.pmd.lang.AbstractPmdLanguageVersionHandler;
import net.sourceforge.pmd.lang.DataFlowHandler;
import net.sourceforge.pmd.lang.Parser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.VisitorStarter;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.dfa.DFAGraphRule;
import net.sourceforge.pmd.lang.plsql.ast.ASTInput;
import net.sourceforge.pmd.lang.plsql.dfa.DFAPLSQLGraphRule;
import net.sourceforge.pmd.lang.plsql.dfa.DataFlowFacade;
import net.sourceforge.pmd.lang.plsql.symboltable.SymbolFacade;

/**
 * Implementation of LanguageVersionHandler for the PLSQL AST. It uses anonymous
 * classes as adapters of the visitors to the VisitorStarter interface.
 *
 * @author sturton - PLDoc - pldoc.sourceforge.net
 */
public class PLSQLHandler extends AbstractPmdLanguageVersionHandler {


    public PLSQLHandler() {
        super(PlsqlProcessingStage.class);
    }

    @Override
    public Parser getParser(ParserOptions parserOptions) {
        return new PLSQLParser(parserOptions);
    }

    @Override
    public DFAGraphRule getDFAGraphRule() {
        return new DFAPLSQLGraphRule();
    }

    @Override
    public DataFlowHandler getDataFlowHandler() {
        return new PLSQLDataFlowHandler();
    }

    @Override
    public VisitorStarter getDataFlowFacade() {
        return new VisitorStarter() {
            @Override
            public void start(Node rootNode) {
                new DataFlowFacade().initializeWith(getDataFlowHandler(), (ASTInput) rootNode);
            }
        };
    }

    @Override
    public VisitorStarter getSymbolFacade() {
        return new VisitorStarter() {
            @Override
            public void start(Node rootNode) {
                new SymbolFacade().initializeWith((ASTInput) rootNode);
            }
        };
    }

}
