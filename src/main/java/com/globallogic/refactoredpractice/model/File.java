package com.globallogic.refactoredpractice.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {

    @Id
    @GeneratedValue(generator = "uuid")
    private String id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    public File(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public byte[] getData() {
        return this.data;
    }

}
