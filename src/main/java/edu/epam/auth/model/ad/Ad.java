package edu.epam.auth.model.ad;

import edu.epam.auth.model.Entity;
import edu.epam.auth.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ad extends Entity {

    private long id;
    private User author;
    private String title;
    private String text;
    private AdType type;
    private BigDecimal price;
    private List<String> images = new ArrayList<>();
    private LocalDate creationDate;
    private LocalDate updateDate;
    private AdStatus adStatus;
    private Category category;

    public Ad() {
    }

    public long getId() {
        return id;
    }

    public Ad setId(long id) {
        this.id = id;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Ad setAuthorId(User author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Ad setTitle(String title) {
        this.title = title;
        return  this;
    }

    public String getText() {
        return text;
    }

    public Ad setText(String text) {
        this.text = text;
        return this;
    }

    public AdType getType() {
        return type;
    }

    public Ad setType(AdType type) {
        this.type = type;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Ad setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<String> getImages() {
        return new ArrayList<>(images);
    }

    public Ad adImage(String image) {
        images.add(image);
        return this;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Ad setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public Ad setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public AdStatus getAdStatus() {
        return adStatus;
    }

    public Ad setAdStatus(AdStatus adStatus) {
        this.adStatus = adStatus;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Ad setCategory(Category category) {
        this.category = category;
        return this;
    }
}
