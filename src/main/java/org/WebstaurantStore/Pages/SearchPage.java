package org.WebstaurantStore.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage{

    WebDriver driver;

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;

    }

    @FindBy(xpath = "//a[@class='border-gray-400 hover:border-green-800 border-solid border border-r-0 box-border flex items-center justify-center font-semibold h-7-1/2 text-sm leading-none p-0 fill-current hover:text-white w-7-1/2 hover:shadow-inner-green bg-gray-gradient hover:bg-green-gradient rounded-r-md text-gray-800 border-r pagerLink']")
    private WebElement nextResultPageButtonXpath;

    @FindBy(xpath = "//a[contains(@aria-label,'last page')]")
    private WebElement lastResultPageButtonXpath;

    @FindBy(xpath = "//h1[@class='page-header search--title']")
    private WebElement searchResultsHeaderXpath;

    private final By itemDescriptionsLocator = By.xpath("//span[@data-testid='itemDescription']");

    /**
     * Confirm text provided is included in each search result on a specific page
     * @param validationText text to be validated against
     * @return count
     */
    private int validateSearchResultsOnPage(String validationText) {
        List<WebElement> itemDescriptionsFound = driver.findElements(itemDescriptionsLocator);
//        itemDescriptionsFound.forEach(description-> Assert.assertTrue(description.getText().toLowerCase().contains(validationText)));
        int count = 0;
        for (WebElement description : itemDescriptionsFound) {
            if (description.getText().toLowerCase().contains(validationText)) {
                count++;
            } else {
                System.out.println("\tMissing text in Title = " + description.getText());
            }
        }

        return count;
    }

    /**
     * Iterate through multiple pages to confirm search result titles
     * @param validationText text to be validated against
     * @return totalResults == totalValidated
     */
    public boolean validateAllSearchResults(String validationText) {
        int pages = Integer.parseInt(lastResultPageButtonXpath.getText());
        int totalResults = Integer.parseInt(searchResultsHeaderXpath.getText().chars()
                .filter(Character::isDigit)
                .mapToObj(Character::toString)
                .collect(Collectors.joining()));

        int totalValidated = 0;
        int totalFound = 0;
        for (int a = 0; a < pages; a++) {
            totalFound += driver.findElements(itemDescriptionsLocator).size();
            totalValidated += validateSearchResultsOnPage(validationText);
            if (a != pages - 1) {
                clickNextResultPage();
            }
        }

        System.out.println("Results stated on Search Header = " + totalResults);
        System.out.println("Results with Validated = " + totalValidated);
        System.out.println("Total Results found = " + totalFound);
        return totalResults == totalValidated;
    }

    /**
     * Navigate to the next search result page
     */
    public void clickNextResultPage() {
        nextResultPageButtonXpath.click();
    }



}
