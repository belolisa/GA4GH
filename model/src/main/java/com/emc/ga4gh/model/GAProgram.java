package com.emc.ga4gh.model;

public class GAProgram {

    private String commandLine = null;
    private String id = null;
    private String name = null;
    private String prevProgramId = null;
    private String version = null;

    public GAProgram(String commandLine, String id, String name,
                     String prevProgramId, String version) {
        super();
        this.commandLine = commandLine;
        this.id = id;
        this.name = name;
        this.prevProgramId = prevProgramId;
        this.version = version;
    }

    public GAProgram() {
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrevProgramId() {
        return prevProgramId;
    }

    public void setPrevProgramId(String prevProgramId) {
        this.prevProgramId = prevProgramId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
