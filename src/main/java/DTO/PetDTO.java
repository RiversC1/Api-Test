package DTO;

import java.util.List;

public class PetDTO {
    private int id;
    private CategoryDTO category;
    private String name;
    private List<TagsDTO> tags;
    private String status;
    private List<String> photoUrls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TagsDTO>  getTags() {
        return tags;
    }

    public void setTags(List<TagsDTO>  tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrl) {
        this.photoUrls = photoUrl;
    }
}
