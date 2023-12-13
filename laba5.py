import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
dataframe = pd.read_csv('Книга.csv', sep=';')
colors = ['#78C850','#F08030', '#6890F0','#F8D030']
fig, axes = plt.subplots(4,1)
sns.boxplot(x=dataframe["<DATE>"], y=dataframe["<OPEN>"], orient='v', ax=axes[0], palette=colors)
sns.boxplot(x=dataframe["<DATE>"], y=dataframe["<HIGH>"], orient='v', ax=axes[1], palette=colors)
sns.boxplot(x=dataframe["<DATE>"], y=dataframe["<LOW>"], orient='v', ax=axes[2], palette=colors)
sns.boxplot(x=dataframe["<DATE>"], y=dataframe["<CLOSE>"], orient='v', ax=axes[3], palette=colors)
plt.show()

