# Lab report 3
1. <a href="https://alexavndra.github.io/cse15l-lab-reports/lab3#part-1" style="color:#023e8a;">Part 1</a>
2. <a href="https://alexavndra.github.io/cse15l-lab-reports/lab3#part-2" style="color:#023e8a;">Part 2</a>

## Part 1

### Failure inducing input and code for ArrayExamples.java
```java
import static org.junit.Assert.*;
import org.junit.*;

public class ArrayTests {
    @Test
    public void testReverseInPlace2() {
        int[] input1 = {3, 4, 5, 10, 2};
        ArrayExamples.reverseInPlace(input1);
        assertArrayEquals(new int[]{2, 10, 5, 4, 3}, input1);
    }
}
```
In this code cell, this is where the bug is provoked.

### Input that doesn't induce a failure
```java
import static org.junit.Assert.*;
import org.junit.*;

public class ArrayTests {
    @Test
	public void testReverseInPlace() {
        int[] input1 = {2};
        ArrayExamples.reverseInPlace(input1);
        assertArrayEquals(new int[]{2}, input1);
	}
}
```
In this code cell, the bug can't be detected from the input. 

### Symptom with running the tests
![Symptom displayed while running tests](/lab3-assets/symptom.png)
In this test output, there is a bug when running the `testReverseInPlace2()` test, as the expected and output values do not match. </br>

![No symptom displayed while running tests](/lab3-assets/no-symptom.png)
In this output, however, there is no symptoms with running the `testReverseInPlace()` test, since the expected and output values match.

### Finding the bug and resolving the code
#### Code before patching up bug
```java
static void reverseInPlace(int[] arr) {
    for(int i = 0; i < arr.length; i += 1) {
      arr[i] = arr[arr.length - i - 1];
    }
  }
```

#### Code after patching up bug
```java
static void reverseInPlace(int[] arr) {
    int[] copiedArr = new int[arr.length];
    
    // makes sure copiedArr is its own array
    for (int i = 0; i < arr.length; i += 1) {
      copiedArr[i] = arr[i];
    }

    // reverses the array by taking the values from copiedArr and updated the original array
    for(int i = 0; i < arr.length; i += 1) {
      arr[i] = copiedArr[copiedArr.length - 1 - i];
    }
  }
```
This fix addresses the issue in the code in that it actually updates the original array to be reversed correctly, instead of mis-assigning the "reversed" values to the indices. As shown in the original code, for every int `i` in `arr`, the value of `arr[i]` is now equal to whatever value is at the tail-end of the list; though this will work for arrays with a single element, it will mis-assign values to arrays with multiple elements (e.g. the input given to "evoke" the bug).

In this new code, however, I patched the bug by first creating a new array, `copiedArr`, and filling it with the same values as the given `arr`. Next, to make sure that the original array is reversed, the original array is iterated through, and each of the values from the original array are replaced by `copiedArr`, which is being reversed in the same loop. Since I modified the original array, the `copiedArr` doesn't need to be returned.

## Part 2
### grep -A1
#### File
```bash
(base) alexavndra@alexandras-MBP technical % grep -A1 residents ./government/Media/residents_sue_city.txt 
Mobile home residents sue city for loss, moving costs
Wednesday, May 29, 2002
--
Residents and former residents of a Lincoln Place mobile home
park that is being demolished to make way for a city-owned
--
On Friday, a group of residents filed a lawsuit against
Pittsburgh, seeking damages for families who were displaced and
--
Raimondi has been evicting residents and demolishing trailers
that are left behind in order to meet a city requirement that he
--
lawsuit. He said the city still maintains that the residents of the
mobile home park must seek any compensation from Raimondi, not from
```

#### Directory
```bash
(base) alexavndra@alexandras-MBP technical % grep -r -A1 residents ./government/Media 
./government/Media/Federal_agency.txt:residents of Charleston County and coastal South Carolina," the
./government/Media/Federal_agency.txt-records said. The King Street building appears to be vacant.
--
./government/Media/water_fees.txt:Alpaugh residents may see their water fees triple to avoid
./government/Media/water_fees.txt-losing service at the end of the month.
--
./government/Media/BusinessWire2.txt:Dallas County residents, or 13 percent of the county population,
./government/Media/BusinessWire2.txt-live below poverty level. Eleven percent of the Tarrant County
./government/Media/BusinessWire2.txt:population, or 150,000 residents, live below poverty level. The
./government/Media/BusinessWire2.txt-TEAJF grants will allow 39 Texas organizations, five of which are
--
./government/Media/Legal-aid_chief.txt:that Nebraska's low-income residents will be involved in about
./government/Media/Legal-aid_chief.txt-65,000 significant legal matters this year.
--
```
I found the `grep -A[num] [word]` command on [gnu.org](https://www.gnu.org/software/grep/manual/grep.html#Matching-Control); what this command does is that it outputs all the trailing contexts with the word of the *num* lines. This is useful in that it allows us to see the words (context) surrounding the input we give it.</br>

### grep -F
#### File
```bash
(base) alexavndra@alexandras-MBP technical % grep -F health ./biomed/1468-6708-3-1.txt
        Many healthy older adults report gradual weight gain
        most robust health as we age. It has been suggested that
        quality of life or years of healthy life (YHL) in the
        on health than on mortality. If so, then behavior change
        they were evaluated on improved health, rather than on
        years of being healthy, in a cohort of older adults for
          Study design: The Cardiovascular Health
          The Cardiovascular Health Study (CHS) is a
          Years of life and years of healthy life
          which the person was 'healthy', and is similar in concept
          to quality-adjusted life-years, healthy year equivalents,
          self-rated health (is your health excellent, very good,
          of health events in many studies [ 17 ] . Because we are
          examining health status over time, we added a sixth
          health state, dead. Data were available about 93% of the
          good, or good health (were 'healthy'). YHL ranges from 0
          good health) to 7 years (for persons who were healthy
          throughout). Since people reported their health every 6
          drawback of this simple definition of 'healthy' is that
          it does not distinguish between fair or poor health and
          death, since all are considered 'not healthy'. We also
          YHL from their age, sex, and health at the end of 3
          health status, limitations in activities of daily living
        About 78% of the subjects were healthy at baseline,
        not shown). Of the 22% who were unhealthy (fair or poor) at
        baseline, about 24% were healthy 7 years later. There was
        lines: years of unhealthy life (YOL minus YHL) and years
        unhealthy life, and white men the fewest; black men lost
        were healthy (YHL/YOL, not shown); for whites, about 75%
        were healthy.
        unintended weight loss, YOL, YHL, years of unhealthy life,
        YOL, YHL, years of unhealthy life, and years lost to death.
        after adjustment for the entire set of health-related
        an intervention to improve the health of underweight women
        health of the other groups to that of the normals would
          in a consistent way with health suggests that such
          requires specific attention to improved health and
          Interestingly, the strongest health relationships were
          objective was to make the underweight as healthy as their
          CHS participants were somewhat healthier than the
          estimated the last four years of health data for about
          effects of obesity on risk factors for future health. A
          results from many studies. However, health measures
        criticized for emphasizing mortality rather than health. We
        CHS Cardiovascular Health Study
        EVGFP Is your health excellent, very good, good, fair or
        YHL Years of healthy life
```

#### Directory
```bash
(base) alexavndra@alexandras-MBP technical % grep -r -F health ./biomed
./biomed/1471-2172-3-4.txt:          Dr. J. DeGregori (University of Colorado Health Science
./biomed/1471-2466-1-1.txt:        Physiology and Chronic Health Evaluation) II score,
./biomed/1471-2369-3-9.txt:        of Science's Institute of Medicine reported that our health
./biomed/1476-069X-2-4.txt:        health impacts of emissions from specific sources, using a
./biomed/1476-069X-2-4.txt:        with dispersion models and health evidence [ 3 4 5 6 ] .
./biomed/1476-069X-2-4.txt:        power plants on the grid). Quantifying the health benefits
./biomed/1476-069X-2-4.txt:        to predict the public health benefits of increased
./biomed/1476-069X-2-4.txt:        resulting public health benefits would include
./biomed/1476-069X-2-4.txt:        health benefits of insulation in the residential sector.
./biomed/1476-069X-2-4.txt:        health benefits will not necessarily be proportional.
./biomed/1476-069X-2-4.txt:        health benefits. The fuels used in homes also differ both
./biomed/1476-069X-2-4.txt:        health benefits associated with hypothetically retrofitting
./biomed/1476-069X-2-4.txt:        public health benefits compare with the economic savings
./biomed/1476-069X-2-4.txt:        - In general, how uncertain are public health benefit
./biomed/1476-069X-2-4.txt:          in accurately quantifying the health benefits of
./biomed/1476-069X-2-4.txt:          or pollutant precursor (μg/day). If the health effect in
./biomed/1476-069X-2-4.txt:          a health perspective (since the health impacts will be
./biomed/1476-069X-2-4.txt:          Public Health Benefits
./biomed/1476-069X-2-4.txt:          health benefits when health endpoints were placed in
./biomed/1476-069X-2-4.txt:          Although the economic valuation of health endpoints is
./biomed/1476-069X-2-4.txt:          Public Health Benefits
```
With `grep -F`, we "obtain patterns" from the given file/directory input ([gnu.org](https://www.gnu.org/software/grep/manual/grep.html#Matching-Control)). This is useful since we can see which lines/patterns contain the word we input, especially when looking through a directory, as it also includes the file that the word is located in. 

### grep -c
#### File
```bash
(base) alexavndra@alexandras-MBP technical % grep -c Clinton ./911report/chapter-6.txt 
40
```

#### Directory
```bash
(base) alexavndra@alexandras-MBP technical % grep -r -c Clinton ./911report 
./911report/chapter-13.4.txt:36
./911report/chapter-13.5.txt:2
./911report/chapter-13.1.txt:0
./911report/chapter-13.2.txt:0
./911report/chapter-13.3.txt:35
./911report/chapter-3.txt:65
./911report/chapter-2.txt:0
./911report/chapter-1.txt:0
./911report/chapter-5.txt:1
./911report/chapter-6.txt:40
./911report/chapter-7.txt:0
./911report/chapter-9.txt:0
./911report/chapter-8.txt:0
./911report/preface.txt:0
./911report/chapter-12.txt:1
./911report/chapter-10.txt:0
./911report/chapter-11.txt:14
```
With `grep -c`, we see the number of times a word is mentioned either in a file or a directory [cyberciti.biz](https://www.cyberciti.biz/faq/howto-use-grep-command-in-linux-unix/). This would be helpful in examining the count of a certain word across all files, tracking their frequency throughout. 

### grep -i
#### File
```bash
(base) alexavndra@alexandras-MBP technical % grep -i religious ./plos/pmed.0010052.txt
        Many religious traditions and spiritual movements seek perfection [10,11,12,13], but these
```

#### Directory
```bash
(base) alexavndra@alexandras-MBP technical % grep -r -i religious ./plos 
./plos/pmed.0020071.txt:        home, to marry or have children, or to take part in the religious and social activities
./plos/journal.pbio.0020419.txt:        removed the need for religious explanations of natural phenomena. From James Watson's and
./plos/journal.pbio.0020232.txt:        regenerative therapies. The political, ethical, and religious controversies surrounding the
./plos/pmed.0010052.txt:        Many religious traditions and spiritual movements seek perfection [10,11,12,13], but these
./plos/pmed.0020191.txt:        taking place without guidelines—ethical, scientific, moral, or religious. The question
./plos/pmed.0020247.txt:        To see this perhaps more clearly, think of certain religious settings where punishment
./plos/pmed.0020247.txt:        Clergy and other religious leaders are as susceptible as any to the temptation to
./plos/pmed.0020247.txt:        perhaps most vigorously by male-dominant religious organizations.
./plos/journal.pbio.0020439.txt:        approximately 500 years ago—and of its many biological species not described in religious
```
For `grep -i`, we see where a word may be in throughout a file or directory, but with the `-i` flag, it ignores any special cases of the given word, just solely printing it out because it matches the contents ([gnu.org](https://www.gnu.org/software/grep/manual/grep.html#Matching-Control)). This is useful when we want to see the files and directories that may contain the word, regardless of its case usage (more accurate).