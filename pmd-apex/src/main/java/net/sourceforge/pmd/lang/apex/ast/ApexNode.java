/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.apex.ast;

import net.sourceforge.pmd.lang.ast.impl.GenericNode;

import apex.jorje.semantic.ast.AstNode;

/**
 * Root interface implemented by all Apex nodes. Apex nodes wrap a tree
 * obtained from an external parser (Jorje). The underlying AST node is
 * available with {@link #getNode()}.
 *
 * @param <T> Type of the underlying Jorje node
 */
public interface ApexNode<T extends AstNode> extends GenericNode<ApexNode<?>> {

    /**
     * Accept the visitor.
     */
    Object jjtAccept(ApexParserVisitor visitor, Object data);


    /**
     * Get the underlying AST node.
     * @deprecated the underlying AST node should not be available outside of the AST node.
     *      If information is needed from the underlying node, then PMD's AST node need to expose
     *      this information.
     */
    @Deprecated
    T getNode();

    boolean hasRealLoc();

    String getDefiningType();

    String getNamespace();
}
