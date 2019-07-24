import {BaseResourceService} from "../base.resource.service";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export class ${className}ResourceService extends BaseResourceService {
    constructor(public http: Http) {
        super(http);
        this.saveRouter = "${entityPackage}/${lowerNames}";
        this.removeRouter = "${entityPackage}/${lowerNames}";
        this.updateRouter = "${entityPackage}/${lowerNames}";
        this.getRouter = "${entityPackage}/${lowerNames}";
        this.queryRouter = "${entityPackage}/${lowerNames}";
    }
}