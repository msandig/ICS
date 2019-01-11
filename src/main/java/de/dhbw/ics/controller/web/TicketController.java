package de.dhbw.ics.controller.web;

public class TicketController {


/*    @Autowired
    private ReservationManager reservationManager;

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/presentations/{id}")
    public @ResponseBody
    ResponseEntity<Presentation> get(@PathVariable String id) {
        Presentation presentation = null;
        if (id != null && !id.isEmpty()) {
            presentation = this.presentationManager.getPresentation(id);
        }
        return new ResponseEntity<>(presentation, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/service/get/presentations")
    public @ResponseBody
    ResponseEntity<List<Presentation>> getAll(@RequestParam(value = "start") Optional<Long> start, @RequestParam(value = "end") Optional<Long> end) {
        List<Presentation> presentations = null;
        if(start.isPresent()&& end.isPresent()){
            presentations = this.presentationManager.getAllPresentations(start.get(), end.get());
        }else {
            presentations = this.presentationManager.getAllPresentations();
        }

        return new ResponseEntity<List<Presentation>>(presentations, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/service/get/presentations", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
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
