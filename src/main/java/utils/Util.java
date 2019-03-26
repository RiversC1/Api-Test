package utils;

import DTO.CategoryDTO;
import DTO.PetDTO;
import DTO.TagsDTO;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public CategoryDTO fillCategory(int id, String name) {
        CategoryDTO category = new CategoryDTO();
        category.setId(id);
        category.setName(name);

        return category;
    }

    public ArrayList<TagsDTO> fillTaglist(int id, String name) {
        ArrayList<TagsDTO> lista = new ArrayList<TagsDTO>();

        TagsDTO tags = new TagsDTO();
        tags.setId(id);
        tags.setName(name);

        lista.add(tags);

        return lista;
    }

    public PetDTO fillPet(int id, CategoryDTO categoryDTO, String name, List<String> photoUrls, List<TagsDTO> tagsDTOS, String status) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(id);
        petDTO.setCategory(categoryDTO);
        petDTO.setName(name);
        petDTO.setPhotoUrls(photoUrls);
        petDTO.setTags(tagsDTOS);
        petDTO.setStatus(status);

        return petDTO;
    }



}
