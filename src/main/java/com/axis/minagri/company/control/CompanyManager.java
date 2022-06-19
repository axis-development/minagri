package com.axis.minagri.company.control;

import com.axis.minagri.commons.control.Collections;
import com.axis.minagri.company.entity.Company;
import com.axis.minagri.company.entity.CompanyDTO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyManager {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Company saveCompany(CompanyDTO dto) throws Exception {
        Company company = mapCompanyDTOToEntity(dto);
        return entityManager.merge(company);
    }

    public Company findCompanyByName(String companyName) {
        Query query = entityManager.createQuery("select comp from Company comp where comp.companyName = :companyName ");
        query.setParameter("companyName", companyName);
        List<Company> companyList = query.getResultList();
        return Collections.getFirstOrNull(companyList);
    }

    public static Company mapCompanyDTOToEntity(CompanyDTO dto) {
        if(dto == null) {
            return null;
        }
        return Collections.getFirst(mapCompanyDTOToEntity(Arrays.asList(dto)));
    }
    public static List<CompanyDTO> mapCompanyToDTO(List<Company> companyList) {
        if(Collections.nullOrEmpty(companyList)) {
            return Lists.newArrayList();
        }
        List<CompanyDTO> entities = companyList.stream().map(company -> {
            // a simple mapping from domain to dto
            CompanyDTO dto = new CompanyDTO();
            dto.setCompanyName(company.getCompanyName());
            dto.setEmail(company.getEmail());
            dto.setId(company.getId());
            dto.setGsm(company.getGsm());
            dto.setPhone(company.getPhone());
            dto.setVatNumber(company.getVatNumber());
            return dto;
        }).collect(Collectors.toList());
        return entities;
    }

    public static List<Company> mapCompanyDTOToEntity(List<CompanyDTO> companyDTOS) {
        List<Company> entities = companyDTOS.stream().map(dto -> {
            // a simple mapping from domain to dto
            Company company = new Company();
            company.setCompanyName(dto.getCompanyName());
            company.setEmail(dto.getEmail());
            company.setId(dto.getId());
            company.setGsm(dto.getGsm());
            company.setPhone(dto.getPhone());
            company.setVatNumber(dto.getVatNumber());
            //company.setAddress(mapAddressDTOToEntity(dto.getAddress()));
            return company;
        }).collect(Collectors.toList());
        return entities;
    }
}
/*
    @Transactional
    public List<Customer> updateItemsConfig(List<Customer> items) throws Exception {
        items.forEach(itemToUpdated -> {
            Customer item = (Customer) entityManager.createQuery("select item from Customer item where item.id =:itemId ")
                    .setParameter("itemId", itemToUpdated.getId()).getSingleResult();
            item.setItemValue(itemToUpdated.getItemValue());

            entityManager.merge(item);
        });


        return items;
    }

    public List<Customer> findByOrganizationId(Long organizationId) {
        Query query = entityManager.createQuery("select item from Customer item where item.organization.id = :organizationId ");
         query.setParameter("organizationId", organizationId);
         return query.getResultList();
     }

 
    public List<String> getAllOranizationsName() throws Exception {
        Query query = entityManager.createQuery("select org.name from Organization org ORDER BY name ASC ");
        List<String> names = query.getResultList();

        return names;
    }

    public List<ItemDTO> getItemsConfigByOrganizationName(String organizationName) throws Exception {
        Query query = entityManager.createQuery("select org.item from Organization org where org.name =:organizationName");
        query.setParameter("organizationName", organizationName);
        List<Customer> items = query.getResultList();

        return convertItemsConfigToDTO(items);
    }

    public List<OrganizationDTO> getAllOranizations() throws Exception {
        Query query = entityManager.createQuery("select org from Organization org ORDER BY name ASC ");
        List<Organization> entities = query.getResultList();

        return convertOrganizationsToDTO(entities);
    }

    public List<OrganizationDTO> fetchOranizations() throws Exception {
        Query query = entityManager.createQuery("select org from Organization org where org.parentId =0 ORDER BY name ASC ");
        List<Organization> parents = query.getResultList();
        List<OrganizationDTO> dtos = new ArrayList<>();
        for (Organization parent : parents) {
            OrganizationDTO organizationDTO = convertToOrganizationDTO(parent);
            dtos.add(organizationDTO);
            process(organizationDTO, dtos);
        }
        return dtos;
    }

    void process(OrganizationDTO parent, List<OrganizationDTO> dtos) throws Exception {
        Query queryChilds = entityManager.createQuery("select org from Organization org where org.parentId =:parentId");
        queryChilds.setParameter("parentId", parent.getId());
        List<Organization> childs = queryChilds.getResultList();
        for (OrganizationDTO child : convertOrganizationsToDTO(childs)) {
            parent.getChilds().add(child);
            process(child, dtos);
        }
    }



    @Transactional
    public Organization saveOrganization(OrganizationDTO organizationDTO) throws Exception {
         Organization organization =  convertOrganizationDTOToEntity(organizationDTO);
         entityManager.persist(organization);
         return organization;
    }

    public List<OrganizationDTO> convertOrganizationsToDTO(List<Organization> organizations) throws ParseException {
        List<OrganizationDTO> asDto = organizations.stream().map(new Function<Organization, OrganizationDTO>() {
            @Override
            public OrganizationDTO apply(Organization s) {
                // a simple mapping from domain to dto
                OrganizationDTO dto = new OrganizationDTO(s.getId(), s.getName(), s.getParentId());
                return dto;
            }
        }).collect(Collectors.toList());
        return asDto;
    }

    @Transactional
    public void removeOrganizationByName(String organizationName) {
        Organization organizationToRemove = findOrganizationByName(organizationName);
        //remove ItemsConfig
        entityManager.createQuery("delete from Customer item where item.organizationName =:organizationName ")
                .setParameter("organizationName", organizationName).executeUpdate();
        Query query = entityManager.createQuery("delete from Organization org where org.id =:id or org.parentId =:parentId ");
        query.setParameter("id", organizationToRemove.getId());
        query.setParameter("parentId", organizationToRemove.getId());
        query.executeUpdate();
    }

    public List<Organization> convertOrganizationsDTOToEntities(List<OrganizationDTO> organizationsDTO) throws ParseException {
        List<Organization> entities = organizationsDTO.stream().map(new Function<OrganizationDTO, Organization>() {
            @Override
            public Organization apply(OrganizationDTO s) {
                // a simple mapping from domain to dto
                Organization organization = new Organization();
                organization.setName(s.getName());
                organization.setParentId(s.getParentId());
                return organization;
            }
        }).collect(Collectors.toList());
        return entities;
    }

    public Organization convertOrganizationDTOToEntity(OrganizationDTO organizationDTO) throws ParseException {

        return com.migeasy.commons.control.Collections.getFirst(convertOrganizationsDTOToEntities(Arrays.asList(organizationDTO)));
    }

    public OrganizationDTO convertToOrganizationDTO(Organization organization) throws ParseException {

        return Collections.getFirst(convertOrganizationsToDTO(Arrays.asList(organization)));
    }

    public ObjectNode generateJSONfromTree(Tree<String> tree) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = new JsonFactory();
        ByteArrayOutputStream out = new ByteArrayOutputStream(); // buffer to write to string later
        JsonGenerator generator = factory.createJsonGenerator(out, JsonEncoding.UTF8);

        ObjectNode rootNode = generateJSON(tree.getRootElement(), mapper.createObjectNode());
        mapper.writeTree(generator, rootNode);

        return rootNode;
    }

    public ObjectNode generateJSON(Node<String> node, ObjectNode obN) {
        if (node == null) {
            return obN;
        }

        obN.put("text", node.getData());

        ArrayNode childN = obN.arrayNode();
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            return obN;
        }
        obN.set("nodes", childN);
        Iterator<Node<String>> it = node.getChildren().iterator();
        while (it.hasNext()) {
            childN.add(generateJSON(it.next(), new ObjectMapper().createObjectNode()));
        }
        return obN;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }*/
