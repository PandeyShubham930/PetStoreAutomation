package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		// logic to get the data
		String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";// getting path of excel sheet
		XLUtility xlu = new XLUtility(path);
		int rownum = xlu.getRowCount("Sheet 1");
		int colcount = xlu.getCellCount("Sheet 1",1 );

		String apiData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apiData[i - 1][j] = xlu.getCellData("Sheet 1", i, j);
			}
		}
		return apiData;
	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException // single dimensional data provider because we need only user
														// names cols
	{
		String path = System.getProperty("user.dir") + "//testData//UserData.xlsx";// getting path of excel sheet
		XLUtility xlu = new XLUtility(path);
		int rownum = xlu.getRowCount("Sheet 1");

		String apiData[] = new String[rownum];

		for (int i = 1; i <= rownum; i++) {
			apiData[i - 1] = xlu.getCellData("Sheet 1", i, 1);
		}
		return apiData;
	}
}
