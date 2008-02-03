/*
 * ResourceLoader.java
 *
 * Created: 6 mars 2006
 */
package fr.umlv.tatoo.cc.common.velocity;

import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

public class ClassResourceLoader extends ResourceLoader {
  @Override
  public void init(ExtendedProperties configuration) {
    clazz = (Class<?>)configuration.getProperty("resourceClass");
  }

  @Override
  public InputStream getResourceStream(String source)
      throws ResourceNotFoundException {
    InputStream resource = clazz.getResourceAsStream(source);
    if (resource == null)
      throw new ResourceNotFoundException(source);
    return resource;
  }

  @Override
  public boolean isSourceModified(Resource resource) {
    return false;
  }

  @Override
  public long getLastModified(Resource resource) {
    return 0;
  }

  private Class<?> clazz;
}
