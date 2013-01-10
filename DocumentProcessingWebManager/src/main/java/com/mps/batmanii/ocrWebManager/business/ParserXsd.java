package com.mps.batmanii.ocrWebManager.business;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.mps.batmanii.ocrWebManager.beans.AttributeType;
import com.mps.batmanii.ocrWebManager.beans.ComplexType;
import com.mps.batmanii.ocrWebManager.beans.Component;
import com.mps.batmanii.ocrWebManager.beans.ElementType;
import com.mps.batmanii.ocrWebManager.beans.GroupType;
import com.mps.batmanii.ocrWebManager.beans.SimpleType;
import com.mps.batmanii.ocrWebManager.beans.XsdFile;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSContentType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSModelGroup;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSTerm;
import com.sun.xml.xsom.XSType;
import com.sun.xml.xsom.parser.XSOMParser;
import com.sun.xml.xsom.XSRestrictionSimpleType;

public class ParserXsd {
	private final static Logger logger = LoggerFactory
			.getLogger(ParserXsd.class);

	public static void main(String args[]) throws SAXException, IOException {
		ParserXsd rr = new ParserXsd();
		rr.parseSchema();
	}

	@SuppressWarnings("unchecked")
	public XsdFile parse(String filename) throws SAXException {
		XsdFile xsdFile = new XsdFile(filename);
		// logger.info("Filename " + filename);
		File file = null;
		try {
			file = new File(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LinkedList<ElementType> elementList = new LinkedList<ElementType>();
		LinkedList<SimpleType> simpleList = new LinkedList<SimpleType>();
		LinkedList<ComplexType> complexTypes = new LinkedList<ComplexType>();

		XSOMParser parser = new XSOMParser();
		try {
			parser.parse(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		XSSchemaSet sset = parser.getResult();
		XSSchema gtypesSchema = sset.getSchema(1);

		Iterator<XSComplexType> ctiter = gtypesSchema.iterateComplexTypes();
		while (ctiter.hasNext()) {
			XSComplexType ct = (XSComplexType) ctiter.next();
			complexTypes.add(printElements(ct));
		}
		// for (int i = 0; i < complexTypes.size(); i++)

		// System.out.println(complexTypes.get(i));

		// Parse simpleType tag ;
		Map<String, com.sun.xml.xsom.XSSimpleType> simpleTypes = gtypesSchema
				.getSimpleTypes();
		Set<Entry<String, com.sun.xml.xsom.XSSimpleType>> entrySet = simpleTypes
				.entrySet();
		Object[] array = entrySet.toArray();
		for (int i = 0; i < array.length; i++) {
			simpleList
					.add(parseSimpleType(((Entry<String, com.sun.xml.xsom.XSSimpleType>) array[i])
							.getValue()));
		}
		// System.out.println(simpleList);

		gtypesSchema.getElementDecls();
		Map<String, XSElementDecl> elementDecls = gtypesSchema
				.getElementDecls();
		Set<Entry<String, XSElementDecl>> entrySet2 = elementDecls.entrySet();
		Object[] array2 = entrySet2.toArray();

		for (int i = 0; i < array2.length; i++) {
			Entry<String, com.sun.xml.xsom.XSElementDecl> aux = (Entry<String, com.sun.xml.xsom.XSElementDecl>) array2[i];
			XSElementDecl e = (XSElementDecl) aux.getValue();
			XSType t = e.getType();
			XSComplexType xsComplexType = (XSComplexType) t;
			elementList
					.add(new ElementType(printElements(xsComplexType), null,
							new Component(aux.getKey(), aux.getValue()
									.getType(), 1, 1)));
		}
		// for (int i = 0; i < elementList.size(); i++)
		// System.out.println(elementList);
		xsdFile.setComplexTypes(complexTypes);
		xsdFile.setElementType(elementList.get(0));
		xsdFile.setSimpleTypes(simpleList);
		xsdFile.setGroupTypes(null);
		return xsdFile;

	}

	// parse one simpleType element
	public SimpleType parseSimpleType(com.sun.xml.xsom.XSSimpleType aux) {
		SimpleType t = new SimpleType();
		Iterator<? extends XSFacet> j = ((XSRestrictionSimpleType) aux)
				.getDeclaredFacets().iterator();

		t.setName(aux.getName());
		t.setBase(aux.asRestriction().getBaseType());
		Vector<String> enumeration = new Vector<String>();
		// bug FACET_MAXINCLUSIVE)
		while (j.hasNext()) {
			XSFacet facet = j.next();
			if (facet.getName().equals(XSFacet.FACET_ENUMERATION)) {
				enumeration.add(facet.getValue().value);
			}
			if (facet.getName().equals(XSFacet.FACET_MAXINCLUSIVE)) {
				t.setMaxInclusive(facet.getValue().value);
			}
			if (facet.getName().equals(XSFacet.FACET_MININCLUSIVE)) {
				t.setMinInclusive(facet.getValue().value);
			}
			if (facet.getName().equals(XSFacet.FACET_MAXEXCLUSIVE)) {
				t.setMaxExclusive(String.valueOf(Integer.parseInt(facet
						.getValue().value) - 1));
			}
			if (facet.getName().equals(XSFacet.FACET_MINEXCLUSIVE)) {
				t.setMinExclusive(String.valueOf(Integer.parseInt(facet
						.getValue().value) + 1));
			}
			if (facet.getName().equals(XSFacet.FACET_LENGTH)) {
				t.setLength(facet.getValue().value);
			}
			if (facet.getName().equals(XSFacet.FACET_MAXLENGTH)) {
				t.setMaxLength(facet.getValue().value);
			}
			if (facet.getName().equals(XSFacet.FACET_MINLENGTH)) {
				t.setMinLength(facet.getValue().value);
			}
			if (facet.getName().equals(XSFacet.FACET_PATTERN)) {
				t.setPattern(facet.getValue().value);
			}
			if (facet.getName().equals(XSFacet.FACET_TOTALDIGITS)) {
				t.setTotalDigits(facet.getValue().value);
			}
		}
		if (enumeration.size() > 0) {
			t.setEnumeration(enumeration);
		}
		return t;
	}

	private LinkedList<AttributeType> getAttributes(XSComplexType xsComplexType) {
		LinkedList<AttributeType> listAttribute = new LinkedList<AttributeType>();
		Collection<? extends XSAttributeUse> c = xsComplexType
				.getAttributeUses();
		Iterator<? extends XSAttributeUse> i = c.iterator();
		while (i.hasNext()) {
			com.sun.xml.xsom.XSAttributeDecl attributeDecl = i.next().getDecl();
			listAttribute.add(new AttributeType(attributeDecl.getName(),
					attributeDecl.getType(), attributeDecl.getDefaultValue(),
					attributeDecl.getDefaultValue(), null));
		}
		return listAttribute;
	}

	private GroupType parseGroup(XSModelGroup xsModelGroup) {
		LinkedList<ElementType> groupElement = new LinkedList<ElementType>();
		XSParticle[] particles = xsModelGroup.getChildren();
		for (XSParticle p : particles) {
			XSTerm pterm = p.getTerm();
			if (pterm.isElementDecl()) { // xs:element inside complex type
				XSType t = pterm.asElementDecl().getType();
				if (t.isComplexType()) {
					XSComplexType xsComplexType1 = (XSComplexType) t;
					// printElements(xsComplexType1);
					groupElement
							.add(new ElementType(printElements(xsComplexType1),
									null, new Component(pterm.asElementDecl()
											.getName(), pterm.asElementDecl()
											.getType(), p.getMinOccurs(), p
											.getMaxOccurs())));
				}
				if (t.isSimpleType()) {
					groupElement.add(new ElementType(null, null, new Component(
							pterm.asElementDecl().getName(), pterm
									.asElementDecl().getType(), p
									.getMinOccurs(), p.getMaxOccurs())));
				}
			}
		}
		return new GroupType(groupElement);
	}

	private ComplexType printElements(XSComplexType xsComplexType) {

		LinkedList<ElementType> auxElement = new LinkedList<ElementType>();
		LinkedList<GroupType> groupTypes = new LinkedList<GroupType>();
		XSContentType xsContentType = xsComplexType.getContentType();
		XSParticle particle = xsContentType.asParticle();
		ComplexType complexTag;

		// probabil bag la minOccurs maxOccurs
		if (particle != null) {
			complexTag = new ComplexType(this.getAttributes(xsComplexType),
					new Component(xsComplexType.getName(),
							xsComplexType.getBaseType(),
							particle.getMinOccurs(), particle.getMaxOccurs()));
		} else {
			complexTag = new ComplexType(this.getAttributes(xsComplexType),
					new Component(xsComplexType.getName(),
							xsComplexType.getBaseType(), 1, 1));
		}

		if (particle != null) {
			XSTerm term = particle.getTerm();
			if (term.isModelGroup()) {
				XSModelGroup xsModelGroup = term.asModelGroup();
				XSParticle[] particles = xsModelGroup.getChildren();
				for (XSParticle p : particles) {
					XSTerm pterm = p.getTerm();
					if (pterm.isElementDecl()) { // xs:element inside complex
													// type
						XSType t = pterm.asElementDecl().getType();

						if (t.isComplexType()) {
							XSComplexType xsComplexType1 = (XSComplexType) t;
							// printElements(xsComplexType1);
							auxElement.add(new ElementType(
									printElements(xsComplexType1), null,
									new Component(pterm.asElementDecl()
											.getName(), pterm.asElementDecl()
											.getType(), p.getMinOccurs(), p
											.getMaxOccurs())));
						}
						if (t.isSimpleType()) {
							auxElement.add(new ElementType(null, null,
									new Component(pterm.asElementDecl()
											.getName(), pterm.asElementDecl()
											.getType(), p.getMinOccurs(), p
											.getMaxOccurs())));
						}
					}
					if (pterm.isModelGroupDecl()) {

						// System.out.println(pterm.asModelGroupDecl().getName()+
						// " ");
						// XSParticle[] particles1 =
						// pterm.asModelGroupDecl().getModelGroup().getChildren();
						groupTypes.add(parseGroup(pterm.asModelGroupDecl()
								.getModelGroup()));
					}
				}

			}
		} else {
		}
		complexTag.setElem(auxElement);
		complexTag.setGroup(groupTypes);
		return complexTag;
	}

	@SuppressWarnings("unchecked")
	public void parseSchema() throws SAXException, IOException {
		// File file = new File("D:\\tmp\\books.xsd");
		File file = new File(
				"C:\\Users\\comy\\Documents\\GitHub\\Batmanii\\schemas\\rotate.xsd");
		LinkedList<ElementType> elementList = new LinkedList<ElementType>();
		LinkedList<SimpleType> simpleList = new LinkedList<SimpleType>();
		LinkedList<ComplexType> complexTypes = new LinkedList<ComplexType>();

		XSOMParser parser = new XSOMParser();
		parser.parse(file);
		XSSchemaSet sset = parser.getResult();
		XSSchema gtypesSchema = sset.getSchema(1);

		Iterator<XSComplexType> ctiter = gtypesSchema.iterateComplexTypes();
		while (ctiter.hasNext()) {
			XSComplexType ct = (XSComplexType) ctiter.next();
			// String typeName = ct.getName();
			// these are extensions so look at the base type to see what it is
			// String baseTypeName = ct.getBaseType().getName();
			complexTypes.add(printElements(ct));
		}
		for (int i = 0; i < complexTypes.size(); i++)

			System.out.println(complexTypes.get(i));

		// Parse simpleType tag ;
		Map<String, com.sun.xml.xsom.XSSimpleType> simpleTypes = gtypesSchema
				.getSimpleTypes();
		Set<Entry<String, com.sun.xml.xsom.XSSimpleType>> entrySet = simpleTypes
				.entrySet();
		Object[] array = entrySet.toArray();
		for (int i = 0; i < array.length; i++) {
			simpleList
					.add(parseSimpleType(((Entry<String, com.sun.xml.xsom.XSSimpleType>) array[i])
							.getValue()));
		}
		System.out.println(simpleList);

		gtypesSchema.getElementDecls();
		Map<String, XSElementDecl> elementDecls = gtypesSchema
				.getElementDecls();
		Set<Entry<String, XSElementDecl>> entrySet2 = elementDecls.entrySet();
		Object[] array2 = entrySet2.toArray();

		for (int i = 0; i < array2.length; i++) {
			Entry<String, com.sun.xml.xsom.XSElementDecl> aux = (Entry<String, com.sun.xml.xsom.XSElementDecl>) array2[i];
			XSElementDecl e = (XSElementDecl) aux.getValue();
			XSType t = e.getType();
			XSComplexType xsComplexType = (XSComplexType) t;
			elementList
					.add(new ElementType(printElements(xsComplexType), null,
							new Component(aux.getKey(), aux.getValue()
									.getType(), 1, 1)));
		}
		for (int i = 0; i < elementList.size(); i++)

			System.out.println(elementList);
	}
}