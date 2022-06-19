package com.axis.minagri.company.boundary;

import com.axis.minagri.company.control.CompanyManager;
import com.axis.minagri.company.entity.CompanyDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CompanyController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    CompanyManager companyManager;

    @RequestMapping(method = RequestMethod.POST, value = "/company/saveBizProfil")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity saveBizProfil(@RequestBody CompanyDTO companyDTO) {
        try {
            companyManager.saveCompany(companyDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(companyDTO);
        }
        catch(Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(companyDTO);
        }
    }

    /*@RequestMapping(value = "/project/fetchCalendarConfig", method = RequestMethod.GET)
    @ResponseBody
    public ObjectNode fetchCalendarConfig() {
        return projectManager.fetchCalendarConfig();
    }

    @RequestMapping(value = "/project/fetchProjects", method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectDTO> fetchProjects() {
        return projectManager.fetchAllProject();
    }

    @RequestMapping(value = "/project/fetchGroups", method = RequestMethod.GET)
    @ResponseBody
    public List<GroupDTO> fetchGroups() {
        return projectManager.fetchAllGroups();
    }

    @RequestMapping(value = "/project/fetchStatus", method = RequestMethod.GET)
    @ResponseBody
    public Status[] fetchStatus() {
        return Status.values();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/saveGroup")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity saveGroup(@RequestBody GroupDTO group) {
        try {
            projectManager.saveGroup(group);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(group);

        } catch (DataIntegrityViolationException e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("The Group " + group.getName() + " already exist");
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(group);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/saveProject")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity saveProject(@RequestBody ProjectDTO projectDTO) {
        try {
            projectManager.saveProject(projectDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(projectDTO);
        } catch (DataIntegrityViolationException e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("The identifier  " + projectDTO.getIdentifier() + " already exist");
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(projectDTO);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/findByCriteria")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectDTO> findByCriteria(@RequestBody ProjectCriteria projectCriteria) {
        return projectManager.findByCriteria(projectCriteria);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/removeProject/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity removeProject(@PathVariable String id) {

        try {
            projectManager.removeProject(Long.valueOf(id));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(id);
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(id);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/removeGroup/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity removeGroup(@PathVariable String id) {

        try {
            projectManager.removeGroup(Long.valueOf(id));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(id);
        } catch (Exception e) {
            logger.error(e);
            if (e instanceof PersistenceException) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("This Group is linked to an project !!");
            }
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when try to delete the Group !!");
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/findProject/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity findProject(@PathVariable String id) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(projectManager.findProject(Long.valueOf(id)));
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when try to find the project " + id);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/project/findGroup/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity findGroup(@PathVariable String id) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(projectManager.findGroupProjectById(Long.valueOf(id)));
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when try to find the group " + id);
        }

    }
*/
}
