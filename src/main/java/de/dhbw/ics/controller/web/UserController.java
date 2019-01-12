package de.dhbw.ics.controller.web;

public class UserController {


   /* @Autowired
    private PresentationManager presentationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/user/{id}")
    public @ResponseBody
    ResponseEntity<User> get(@PathVariable String id) {
        Presentation presentation = null;
        if (id != null && !id.isEmpty()) {
            user = this.presentationManager.getPresentation(id);
        }
        return new ResponseEntity<>(presentation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/user", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<Object> post(@RequestBody Presentation presentation) {
        boolean result = this.presentationManager.persistPresentation(presentation);
        if(result){
            return new ResponseEntity<>(presentation, HttpStatus.OK);
        }
        return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/service/get/presentations/{id}")
    public @ResponseBody
    ResponseEntity<String> delete(@PathVariable String id) {
        boolean result = false;
        if (id != null && !id.isEmpty()) {
            result = this.presentationManager.deletePresentation(id);
        }
        if (result) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>("FAILED", HttpStatus.EXPECTATION_FAILED);
    }*/

}
