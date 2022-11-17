package org.modelix.model.repositoryconcepts.structure;

/*Generated by MPS */

import jetbrains.mps.lang.core.structure.BaseConcept;
import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3Af2f39a18-fd23-4090-b7f2-ba8da340eec2%28org.modelix.model.repositoryconcepts.structure%29%2F2206727074858242403
 */
public class ModuleFacet extends BaseConcept {

  public class Properties extends BaseConcept.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return ModuleFacet.this.getINode();
    }
  }
  public class Children extends BaseConcept.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return ModuleFacet.this.getINode();
    }
  }
  public class References extends BaseConcept.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return ModuleFacet.this.getINode();
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public ModuleFacet(INode node) {
    super(node);
    this.properties = new Properties();
    this.children = new Children();
    this.references = new References();
  }
  public Properties getProperties() {
    return this.properties;
  }
  public Children getChildren() {
    return this.children;
  }
  public References getReferences() {
    return this.references;
  }
}