package University.Schedule.structure;

/*Generated by MPS */

import jetbrains.mps.lang.core.structure.BaseConcept;
import org.modelix.mps.apigen.runtime.INodeHolder;
import org.jetbrains.annotations.NotNull;
import org.modelix.model.api.INode;
import org.modelix.mps.apigen.runtime.MPSLanguageRegistry;

/**
 * Generated for http://127.0.0.1:63320/node?ref=r%3Adfa26643-4653-44bc-9dfe-5a6581bcd381%28University.Schedule.structure%29%2F1648392019017048463
 */
public class Assignment extends BaseConcept {

  public class Properties extends BaseConcept.Properties implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return Assignment.this.getINode();
    }
  }
  public class Children extends BaseConcept.Children implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return Assignment.this.getINode();
    }
  }
  public class References extends BaseConcept.References implements INodeHolder {

    @NotNull
    @Override
    public INode getINode() {
      return Assignment.this.getINode();
    }
    @NotNull
    public Lecture getLecture() {
      return MPSLanguageRegistry.INSTANCE.getInstance(getINode().getReferenceTarget("lecture"));
    }
    @NotNull
    public Lecture setLecture(@NotNull Lecture value) {
      getINode().setReferenceTarget("lecture", value.getINode());
      return value;
    }


  }

  private final Properties properties;
  private final Children children;
  private final References references;
  public Assignment(INode node) {
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
