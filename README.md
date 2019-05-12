# JAX-SB

> Xml Schema Binding

[![Build Status](https://travis-ci.org/jaxsb/jaxsb.png)](https://travis-ci.org/jaxsb/jaxsb)
[![Coverage Status](https://coveralls.io/repos/github/jaxsb/jaxsb/badge.svg)](https://coveralls.io/github/jaxsb/jaxsb)
[![Javadocs](https://www.javadoc.io/badge/org.jaxsb/jaxsb.svg)](https://www.javadoc.io/doc/org.jaxsb/jaxsb)
[![Released Version](https://img.shields.io/maven-central/v/org.jaxsb/jaxsb.svg)](https://mvnrepository.com/artifact/org.jaxsb/jaxsb)

## Introduction

**JAX-SB** is a Java framework for binding to XML Schemas. Based on the CohesionFirst approach, the **JAX-SB** framework provides a complete solution for cohesive integration of Java applications to XML Documents via XML Schemas. The framework provides a [JAX-SB Java Source Code Generator][generator] and a [JAX-SB Runtime][runtime] to parse and marshal XML Documents to and from Java objects. The **JAX-SB** framework is a complete implementation and covers the entirety of the [XSD 1.1 Specification][xsd-spec].

## Why **JAX-SB**?

### CohesionFirst

Developed with the CohesionFirst approach, **JAX-SB** is reliably designed, consistently implemented, and straightforward to use. Made possible by the rigorous conformance to design patterns and best practices in every line of its implementation, **JAX-SB** is a complete binding solution of the entire XSD specification. The **JAX-SB** solution differentiates itself from the rest with the strength of its cohesion to the Java language and the XML Schema model.

### Supports entire XSD Specification

**JAX-SB** supports all directives of the XSD Specification, and generates bindings with highest degree of cohesion possible with the Java language. **JAX-SB** supports the following:

#### Namespaces

**JAX-SB** provides complete binding to the namespaces and types defined in XML Schemas (`import` and `include`), and preserves prefix definitions.

#### Structural

**JAX-SB** provides binding to `simpleType`, `complexType`, `element`, `group`, `attribute`, `attributeGroup`, `notation`, `any` and `anyAttribute` structural types.

#### Non-structural

**JAX-SB** provides binding to `annotation`, `documentation`, `key`, `keyref` and `unique`.

#### Relational

**JAX-SB** provides binding to `xs:type`, `xs:ref`, `all`, `sequence`, `choice`,

#### Definition facets

**JAX-SB** provides binding to the `complexContent`, `restriction`, `extension`, `list` and `union` facets.

#### `restriction` facets

**JAX-SB** provides binding to the `enumeration`, `pattern` and `[min|max][Inclusive|Exclusive]`.

#### `xsi:type` and `redefine` facets

**JAX-SB** properly binds to elements that use the `xsi:type` and `redefine` directives, which is represented by Java's class inheritance model.

## Getting Started

### Prerequisites

* [Java 8][jdk8-download] - The minimum required JDK version.
* [Maven][maven] - The dependency management system.

### Example

1. In your preferred development directory, create a [`maven-archetype-quickstart`][maven-archetype-quickstart] project.

  ```bash
  mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
  ```

2. Create a `example.xsd` XML Schema and put it in `src/main/resources/`.

  ```xml
  <xs:schema
    elementFormDefault="qualified"
    targetNamespace="http://mycompany.com/app/example.xsd"
    xmlns:xs="http://www.w3.org/2001/XMLSchema">

    <xs:element name="example">
      <xs:complexType>
        <xs:sequence>
          <xs:element name="hello">
            <xs:complexType>
              <xs:attribute name="world" type="xs:boolean" use="required"/>
            </xs:complexType>
          </xs:element>
        </xs:sequence>
      </xs:complexType>
    </xs:element>

  </xs:schema>
  ```

3. Add the [`org.jaxsb:jaxsb-maven-plugin`][jaxsb-maven-plugin] to the POM.

  ```xml
  <plugin>
    <groupId>org.jaxsb</groupId>
    <artifactId>jaxsb-maven-plugin</artifactId>
    <version>2.1.4</version>
    <executions>
      <execution>
        <goals>
          <goal>generate</goal>
        </goals>
        <configuration>
          <destDir>${project.build.directory}/generated-sources/jaxsb</destDir>
          <schemas>
            <schema>src/main/resources/example.xsd</schema>
          </schemas>
        </configuration>
      </execution>
    </executions>
  </plugin>
  ```

4. Add the `org.jaxsb:jaxsb-runtime` dependency to the POM.

  ```xml
  <dependency>
    <groupId>org.jaxsb</groupId>
    <artifactId>runtime</artifactId>
    <version>2.1.4</version>
  </dependency>
  ```

5. Run `mvn install`. Upon successful execution of the `jaxsb-maven-plugin`, a new path will be generated in `target/generated-sources/jaxsb`. Add this path to your Build Paths in your IDE to integrate into your project. A class by the name of `com.mycompany.app.example.xe` contains the bindings to `example.xsd`.

6. Create a XML Document in `src/test/resources`.

  ```xml
  <example
    xmlns="http://mycompany.com/app/example.xsd"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://mycompany.com/app/example.xsd ../../main/resources/example.xsd">

    <hello world="true"/>

  </example>
  ```

7. To parse the XML file into Java objects. In your `App.java`:

  ```java
  final ex_example example = (ex_example)Bindings.parse(new InputSource(Resources.getResourceOrFile("example.xml").getURL().openStream());
  ```

8. To marshal JAX-SB Java objects to a XML String:

  ```java
  System.out.println(DOMs.domToString(Bindings.marshal(example), DOMStyle.INDENT));
  ```

### Samples

Samples are provided in the JAX-SB source code:

```bash
git clone git@github.com:jaxsb/jaxsb.git
cd jaxsb/sample
mvn install
```

### Tutorials

Tutorials are provided in the JAX-SB source code:

```bash
git clone git@github.com:jaxsb/jaxsb.git
cd jaxsb/tutorial
mvn install
```

## Known Issues

The **JAX-SB** framework is not suitable for processing of large XML Documents (in the 100s of MBs). Built on top of Java's `org.w3c.dom` implementation of the [Document Object Model (DOM) Level 3 Core Specification][dom3], XML Documents parsed and marshalled by **JAX-SB** consume a significant amount of memory. Work is underway for **JAX-SB** v3 to use [Java's SAX Parser][sax-parser], which will allow the framework to process XML Documents of unbounded size.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details.

[dom3]: http://www.w3.org/TR/2004/REC-DOM-Level-3-Core-20040407
[jdk8-download]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[maven-archetype-quickstart]: http://maven.apache.org/archetypes/maven-archetype-quickstart/
[maven]: https://maven.apache.org/
[sax-parser]: https://docs.oracle.com/javase/tutorial/jaxp/sax/
[generator]: /generator
[jaxsb-maven-plugin]: /../../../../jaxsb/jaxsb-maven-plugin
[runtime]: /runtime
[xsd-spec]: https://www.w3.org/TR/xmlschema11-1/