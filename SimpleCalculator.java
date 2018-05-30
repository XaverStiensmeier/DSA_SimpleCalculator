package dsaFunctions;

public class SimpleCalculator {
	int zN = 1000000; // 1m;
	// chooseWhatToDo

	// methods

	// method

	// ProbabilityMethods
	public double getGroupThrowProbability(int pThrowNumber, int pWantedLevel, int pValue1, int pValue2, int pValue3,
			int pTalentValue, int pModificator) {
		int lCounter = 0;
		for (int i = 0; i < zN; i++) {
			int lQualiCounter = 0;
			for (int x = 0; x < pThrowNumber; x++) {

				int checkTwenty = getTVLeft(pValue1, pValue2, pValue3, pTalentValue, pModificator);
				if (checkTwenty >= 0)
					lQualiCounter = lQualiCounter + getQuality(checkTwenty);
				else if (checkTwenty == -420)
					lQualiCounter = 0;
			}
			if (lQualiCounter >= pWantedLevel) {
				lCounter++;
			}
		}
		return ((double) lCounter / zN);
	}

	public double getThrowProbability(int pValue1, int pValue2, int pValue3, int pTalentValue, int pModificator) {
		int lCounter = 0;
		for (int i = 0; i < zN; i++) {
			if (0 <= getTVLeft(pValue1, pValue2, pValue3, pTalentValue, pModificator))
				lCounter++;
		}
		return ((double) lCounter / zN);
	}

	public double getQualityLevelProbability(int pWantedLevel, int pValue1, int pValue2, int pValue3, int pTalentValue,
			int pModificator) {
		int lCounter = 0;
		for (int i = 0; i < zN; i++) {
			if (isQualityOrGreater(pWantedLevel, getTVLeft(pValue1, pValue2, pValue3, pTalentValue, pModificator)))
				lCounter++;
		}
		return ((double) lCounter / zN);
	}

	// Calculating Values for Probability Methods
	public boolean isQualityOrGreater(int pWantedLevel, int pTVLeft) {
		if (pTVLeft < 0)
			return false;
		return pWantedLevel <= getQuality(pTVLeft);
	}

	public boolean isExactQuality(int pExactWantedLevel, int pTVLeft) {
		return pExactWantedLevel == getQuality(pTVLeft);
	}

	public int getQuality(int pTVLeft) {
		if(pTVLeft<0)
			System.out.println("ERROR");
		return ((pTVLeft - 1) / 3) + 1;
	}

	public boolean isTwenty(int pValue1, int pValue2, int pValue3) {
		return pValue1 == 20 || pValue2 == 20 || pValue3 == 20;
	}
	
	public boolean isCriticalFailure(int pValue1, int pValue2, int pValue3) {
		return (pValue1 == 20 && pValue2 == 20) || (pValue1 == 20 && pValue3 == 20) || (pValue2 == 20 && pValue3 == 20);
	}

	public boolean isTwenty(int pValue1) {
		return pValue1 == 20;
	}

	public int getTVLeft(int pValue1, int pValue2, int pValue3, int pTalentValue, int pModificator) {
		int lThrow1 = diceThrow(20);
		int lThrow2 = diceThrow(20);
		int lThrow3 = diceThrow(20);

		int lCThrow1 = pValue1 - lThrow1 + pModificator;
		int lCThrow2 = pValue2 - lThrow2 + pModificator;
		int lCThrow3 = pValue3 - lThrow3 + pModificator;

		if (!isCriticalFailure(lThrow1, lThrow2, lThrow3)) { //enter isTwenty here if you play a single 20 as a crit. failure.
			if (lCThrow1 < 0)
				pTalentValue = pTalentValue + lCThrow1;
			if (lCThrow2 < 0)
				pTalentValue = pTalentValue + lCThrow2;
			if (lCThrow3 < 0)
				pTalentValue = pTalentValue + lCThrow3;
		} else
			return -420;
		if (pTalentValue >= 0)
			return pTalentValue;
		else
			return -1;

	}

	// DiceThrow methods: Calculate the dice throw
	public int diceThrow(int wNumber) {
		// w6 w10 w20
		return (int) (Math.random() * wNumber + 1);
	}

	public double checkDiceThrow() {
		// eine bestimmte Zahl auf dem Würfel sollte mir 5% wahrscheinlichkeit erreicht
		// werden.
		int lCounterOne = 0;
		for (int i = 0; i < zN; i++)
			if (diceThrow(20) == 1)
				lCounterOne++;
		return (double) lCounterOne / zN;
	}

	public void setN(int pN) {
		zN = pN;
	}
}
