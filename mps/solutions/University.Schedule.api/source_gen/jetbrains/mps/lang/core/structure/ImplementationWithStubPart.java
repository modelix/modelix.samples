package jetbrains.mps.lang.core.structure;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.INodeHolder;

public interface ImplementationWithStubPart extends INodeHolder, ImplementationPart {
  interface Properties extends INodeHolder, ImplementationPart.Properties {
  }
  interface Children extends INodeHolder, ImplementationPart.Children {


  }
  interface References extends ImplementationPart.References, INodeHolder {

  }
  Properties getProperties();
  Children getChildren();
}
