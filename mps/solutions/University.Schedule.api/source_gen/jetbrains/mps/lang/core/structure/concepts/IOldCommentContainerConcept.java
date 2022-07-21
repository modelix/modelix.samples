package jetbrains.mps.lang.core.structure.concepts;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.AbstractConcept;
import jetbrains.mps.lang.core.structure.IOldCommentContainer;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import java.util.HashMap;
import org.modelix.mps.apigen.runtime.MPSProperty;
import org.modelix.mps.apigen.runtime.MPSChildLink;
import org.modelix.mps.apigen.runtime.MPSReferenceLink;
import java.util.List;
import java.util.ArrayList;

public class IOldCommentContainerConcept extends AbstractConcept<IOldCommentContainer> {

  @NotNull
  @Override
  public IOldCommentContainer createInstance(@NotNull INode node) {
    throw new UnsupportedOperationException("concept is abstract!");
  }
  private final HashMap<String, MPSProperty> properties = new HashMap<String, MPSProperty>();
  private final HashMap<String, MPSChildLink> children = new HashMap<String, MPSChildLink>();
  private final HashMap<String, MPSReferenceLink> references = new HashMap<String, MPSReferenceLink>();
  private final List<AbstractConcept<?>> superConcepts = new ArrayList<AbstractConcept<?>>();
  public static final IOldCommentContainerConcept INSTANCE = new IOldCommentContainerConcept();
  private IOldCommentContainerConcept() {
    super(true, "jetbrains.mps.lang.core.structure.IOldCommentContainer", "IOldCommentContainer", "ceab5195-25ea-4f22-9b92-103b95ca8c0c/4123120730935488432");
  }

  @Override
  protected void doInit() {
  }
  @NotNull
  @Override
  protected HashMap<String, MPSProperty> myProperties() {
    return properties;
  }
  @NotNull
  @Override
  protected HashMap<String, MPSChildLink> myChildLinks() {
    return children;
  }
  @NotNull
  @Override
  protected HashMap<String, MPSReferenceLink> myReferenceLinks() {
    return references;
  }
  @NotNull
  @Override
  protected List<AbstractConcept<?>> mySuperConcepts() {
    return superConcepts;
  }
}
