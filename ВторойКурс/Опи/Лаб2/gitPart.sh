git init git-part     #init git-repository

cd git-part


# r0 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
cp -r ../commits/commit0/. ./src
git checkout -b first_branch
git add .
git commit -m 'red did r0'
echo 'red did r0'

# r1 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout -b seventeenth_branch
cp -r ../commits/commit1/. ./src
git add .
git commit -m "blue did r1"
echo 'blue did r1'

# r2 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout -b twentieth_branch
cp -r ../commits/commit2/. ./src
git add .
git commit -m 'red did r2'
echo 'red did r2'

# r3 red
git checkout -b thirteenth_branch
cp -r ../commits/commit3/. ./src
git add .
git commit -m 'red did r3'
echo 'red did r3'

# r4 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout -b third_branch
cp -r ../commits/commit4/. ./src
git add .
git commit -m "blue did r4"
echo 'blue did r4'

# r5 blue
git checkout -b "fourteenth_branch"
cp -r ../commits/commit5/. ./src
git add .
git commit -m "blue did r5"
echo 'blue did r5'

# r6 blue
cp -r ../commits/commit6/. ./src
git add .
git commit -m "blue did r6"
echo 'blue did r6'

# r7 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout first_branch
cp -r ../commits/commit7/. ./src
git add .
git commit -m 'red did r7'
echo 'red did r7'

# r8 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout -b eighteenth_branch
cp -r ../commits/commit8/. ./src
git add .
git commit -m "blue did r8"
echo 'blue did r8'

# r9 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout thirteenth_branch
cp -r ../commits/commit9/. ./src
git add .
git commit -m 'red did r9'
echo 'red did r9'

# r10 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiterium.ru"
git checkout thirteenth_branch
git checkout -b fifth_branch
cp -r ../commits/commit10/. ./src
git add .
git commit -m "blue did 10"
echo 'blue did 10'

# r11 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout thirteenth_branch
cp -r ../commits/commit11/. ./src
git add .
git commit -m 'red did r11'
echo 'red did r11'

# r12 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout -b second_branch
cp -r ../commits/commit12/. ./src
git add .
git commit -m "blue did r12"
echo 'blue did r12'

# r13 red
git checkout thirteenth_branch
cp -r ../commits/commit13/. ./src
git add .
git commit -m 'red did r13'
echo 'red did r13'

# r14 red
git checkout first_branch
cp -r ../commits/commit14/. ./src
git add .
git commit -m 'red did r14'
echo 'red did r14'

# r15 blue
git config --global user.name "blue"
git config --global user.name "blue@jupiter.ru"
git checkout -b fourth_branch
cp -r ../commits/commit15/. ./src
git add .
git commit -m "blue did r15"
echo 'blue did r15'

# r16 blue
git checkout -b ninth_branch
cp -r ../commits/commit16/. ./src
git add .
git commit -m "blue did r16"
echo 'blue did r16'

# r17
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout -b fifteenth_branch
cp -r ../commits/commit17/. ./src
git add .
git commit -m 'red did r17'
echo 'red did r17'

# r18 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout -b tenth_branch
cp -r ../commits/commit18/. ./src
git add .
git commit -m "blue did 18"
echo 'blue did 18'

# r19 blue
git checkout ninth_branch
cp -r ../commits/commit19/. ./src
git add .
git commit -m "blue did r19"
echo 'blue did r19'

# r20 blue
git checkout eighteenth_branch
cp -r ../commits/commit20/. ./src
git add .
git commit -m "blue did r20"
echo 'blue did r20'

# r21 blue
git checkout -b sixth_branch
cp -r ../commits/commit21/. ./src
git add .
git commit -m "blue did r21"
echo 'blue did r21'

# r22 blue
cp -r ../commits/commit22/. ./src
git add .
git commit -m "blue did r22"
echo 'blue did r22'

# r23 blue
git checkout sixteenth_branch
cp -r ../commits/commit23/. ./src
git add .
git commit -m "blue did r23"
echo 'blue did r23'

# r24 blue
git checkout tenth_branch
cp -r ../commits/commit24/. ./src
git add .
git commit -m "blue did r24"
echo 'blue did r24'

# r25 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout thirteenth_branch
cp -r ../commits/commit25/. ./src
git add .
git commit -m 'red did r25'
echo 'red did r25'

# r26 red
git checkout -b seventh_branch
cp -r ../commits/commit26/. ./src
git add .
git commit -m 'red did r26'
echo 'red did r26'


# r27 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout fourteenth_branch
cp -r ../commits/commit27/. ./src
git add .
git commit -m "blue did r27"
echo 'blue did r27'


# r28
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout -b eleventh_branch
cp -r ../commits/commit28/. ./src
git add .
git commit -m 'red did r28'
echo 'red did r28'

# r29
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout twentieth_branch
cp -r ../commits/commit29/. ./src
git add .
git commit -m 'red did r29'
echo 'red did r29'

# r30 red
git checkout eleventh_branch
cp -r ../commits/commit30/. ./src
git add .
git commit -m 'red did r30'
echo 'red did r30'

# r31 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout second_branch
cp -r ../commits/commit31/. ./src
git add .
git commit -m "blue did r31"
echo 'blue did r31'

# r32 blue
git checkout sixth_branch
cp -r ../commits/commit32/. ./src
git add .
git commit -m "blue did r32"
echo 'blue did r32'

# r33 blue
cp -r ../commits/commit33/. ./src
git add .
git commit -m "blue did r33"
echo 'blue did r33'

# r34 blue
git checkout -b twelfth_branch
cp -r ../commits/commit34/. ./src
git add .
git commit -m "blue did r34"
echo 'blue did r34'

# r35 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout twentieth_branch
cp -r ../commits/commit35/. ./src
git add .
git commit -m 'red did r35'
echo 'red did r35'

# r36 red
git checkout fifteenth_branch
cp -r ../commits/commit36/. ./src
git add .
git commit -m 'red did r36'
echo 'red did r36'

# r37 red
git checkout first_branch
cp -r ../commits/commit37/. ./src
git add .
git commit -m 'red did r37'
echo 'red did r37'

# r38 red
git checkout twentieth_branch
cp -r ../commits/commit38/. ./src
git add .
git commit -m 'red did r38'
echo 'red did r38'

# r39 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout fifth_branch
cp -r ../commits/commit39/. ./src
git add .
git commit -m "blue did r39"
echo 'blue did r39'

# r40 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout eleventh_branch
cp -r ../commits/commit40/. ./src
git add .
git commit -m 'red did r40'
echo 'red did r40'


# r41 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout sixth_branch
cp -r ../commits/commit41/. ./src
git add .
git commit -m "blue did r41"
echo 'blue did r41'

# r42 blue
cp -r ../commits/commit42/. ./src
git add .
git commit -m "blue did r42"
echo 'blue did r42'

# r43 blue DO MERGE 🥹
git checkout fourth_branch
if git merge sixth_branch --no-commit; then
	echo 'Осилили мержик 😝'
else
	echo 'не осилили мержик 🤬'
fi
exit 0
cp -r ../commits/commit43/. ./src
git add .
git commit -m "blue did r43"
echo 'blue did r43'

# r44 blue
git checkout fourteenth_branch
cp -r ../commits/commit44/. ./src
git add .
git commit -m "blue did r44"
echo 'blue did r44'

# r45 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout seventh_branch
cp -r ../commits/commit45/. ./src
git add .
git commit -m 'red did r45'
echo 'red did r45'

# r46 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout twelfth_branch
cp -r ../commits/commit46/. ./src
git add .
git commit -m "blue did r46"
echo 'blue did r46'

# r47 blue DO MERGE 🥹
git checkout tenth_branch
if git merge twelfth_branch --no-commit; then
	echo 'оформили мержик 😝'
else
	echo 'заруинили мержик 🤬'
fi
cp -r ../commits/commit47/. ./src
git add .
git commit -m "blue did r47"
echo 'blue did r47'

# r48 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout seventh_branch
cp -r ../commits/commit48/. ./src
git add .
git commit -m 'red did r48'
echo 'red did r48'


# r49 red
git checkout twentieth_branch
cp -r ../commits/commit49/. ./src
git add .
git commit -m 'red did r49'
echo 'red did r49'

# r50 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout fourteenth_branch
cp -r ../commits/commit50/. ./src
git add .
git commit -m "blue did r50"
echo 'blue did r50'

# r51 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout twentieth_branch
cp -r ../commits/commit51/. ./src
git add .
git commit -m 'red did r51'
echo 'red did r51'

# r52 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout seventeenth_branch
cp -r ../commits/commit52/. ./src
git add .
git commit -m "blue did r52"
echo 'blue did r52'

# r53 blue DO MERGE 🥹
git checkout fourteenth_branch
if git merge seventeenth_branch --no-commit;then
	echo 'залили мержик 😝'
else
	echo 'слили мержик 🤬'
fi
cp -r ../commits/commit53/. ./src
git add .
git commit -m "blue did r53"
echo 'blue did r53'

# r54 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout eleventh_branch
cp -r ../commits/commit54/. ./src
git add .
git commit -m 'red did r54'
echo 'red did r54'

# r55 red
git checkout fifteenth_branch
cp -r ../commits/commit55/. ./src
git add .
git commit -m 'red did r55'
echo 'red did r55'


# r56 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout -b nineteenth_branch
cp -r ../commits/commit56/. ./src
git add .
git commit -m "blue did r56"
echo 'blue did r56'

# r57 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout twentieth_branch
cp -r ../commits/commit57/. ./src
git add .
git commit -m 'red did r57'
echo 'red did r57'


# r58 blue DO MERGE 🥹
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout tenth_branch
if git merge twentieth_branch --no-commit; then
	echo 'оформили мержик 😝'
else
	echo 'профукали мержик 🤬'
fi
cp -r ../commits/commit58/. ./src
git add .
git commit -m "bue did r58"
echo 'blue did r58'

# r59 blue DO MERGE 🥹
git checkout fourteenth_branch
if git merge tenth_branch --no-commit;then
	echo 'запустили мержик 😝'
else
	echo 'уронили мержик 🤬'
fi
cp -r ../commits/commit59/. ./src
git add .
git commit -m "blue did r59"
echo 'blue did r59'

# r60 blue
git checkout fourth_branch
cp -r ../commits/commit60/. ./
git add .
git commit -m "blue did r60"
echo 'blue did r60'

# r61 blue
cp -r ../commits/commit61/. ./src
git add .
git commit -m "blue did r61"
echo 'blue did r61'

# r62 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout fifteenth_branch
cp -r ../commits/commit62/. ./src
git add .
git commit -m "red did r62"
echo "red did r62"

# r63 red
git checkout -b sixteenth_branch
cp -r ../commits/commit63/. ./src
git add .
git commit -m "red did r63"
if git merge fifteenth_branch --commit;then
    echo "Смогли мерджик сделать 😝"
else
    echo "Не осилили слияние 🤬"
fi

# r64 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout eighteenth_branch
cp -r ../commits/commit64/. ./src
git add .
git commit -m "blue did r64"
echo 'blue did r64'

# r65 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout thirteenth_branch
cp -r ../commits/commit65/. ./src
git add .
git commit -m 'red did r65'
echo 'red did r65'

# r66 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout fifth_branch
cp -r ../commits/commit66/. ./src
git add .
git commit -m "blue did r66"
echo 'blue did r66'

# r67 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout first_branch
cp -r ../commits/commit67/. ./src
git add .
git commit -m 'red did r67'
echo 'red did r67'


# r68 blue
git config --global user.name "blue"
git config --global user.name "blue@jupiter.ru"
git checkout fourteenth_branch
cp -r ../commits/commit68/. ./src
git add .
git commit -m "blue did r68"
echo 'blue did r68'

# r69 blue DO MERGE 🥹
git checkout eighteenth_branch
if git merge fourteenth_branch --no-commit; then
	echo 'сделали мержик 😝'
else
	echo 'не осилили слияние 🤬'
fi
cp -r ../commits/commit69/. ./src
git add .
git commit -m "blue did r69"
echo 'blue did r69'

# r70 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout thirteenth_branch
cp -r ../commits/commit70/. ./src
git add .
git commit -m 'red did r70'
echo 'red did r70'

# r71 red
git checkout seventh_branch
cp -r ../commits/commit71/. ./src
git add .
git commit -m 'red did r71'
echo 'red did r71'

# r72 blue DO MERGE 🥹
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout fifth_branch
if git merge seventh_branch --no-commit; then
	echo 'есть мерж 😝'
else
	echo 'нет мержа 🤬'
fi
cp -r ../commits/commit72/. ./src
git add .
git commit -m "blue did r72"
echo 'blue did r72'

# r73 blue
git checkout fourth_branch
cp -r ../commits/commit73/. ./src
git add .
git commit -m "blue did r73"
echo 'blue did r73'

# r74 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout first_branch
cp -r ../commits/commit74/. ./src
git add .
git commit -m 'red did r74'
echo 'red did r74'

# r75 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout third_branch
cp -r ../commits/commit75/. ./src
git add .
git commit -m "blue did r75"
echo 'blue did r75'

# r76 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout thirteenth_branch
if git merge third_branch --no-commit;then
    echo "Смогли мерджик сделать 😝"
else
    echo "Не осилили слияние 🤬"
fi
cp -r ../commits/commit76/. ./src
git add .
git commit -m 'red did r76'
echo 'red did r76'


# r77 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout seventeenth_branch
cp -r ../commits/commit77/. ./src
git add .
git commit -m "blue did r77"
echo "blue did r77"

# r78 blue
git checkout second_branch
cp -r ../commits/commit78/. ./src
git add .
git commit -m "blue did r78"
echo 'blue did r78'

# r79 blue DO MERGE 🥹
git checkout fifteenth_branch
if git merge second_branch --no-commit; then
	echo 'мержик 😝'
else
	echo 'нет мержика 🤬'
fi
cp -r ../commits/commit79/. ./src
git add .
git commit -m "blue did r79"
echo 'blue did r79'

# r80 blue DO MERGE 🥹
git checkout eighteenth_branch
if git merge fifteenth_branch --no-commit; then
	echo 'сделали мерж 😝'
else
	echo 'не сделали мерж 🤬'
fi
cp -r ../commits/commit80/. ./src
git add .
git commit -m "blue did r80"
echo 'blue did r80'

# r81 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout first_branch
if git merge third_branch --no-commit;then
    echo "Смогли мерджик сделать 😝"
else
    echo "Не осилили слияние 🤬"
fi
cp -r ../commits/commit81/. ./src
git add .
git commit -m 'red did r81'
echo 'red did r81'

# r82 red
git checkout -b eighth_branch
cp -r ../commits/commit82/. ./src
git add .
git commit -m 'red did r82'
echo 'red did r82'

# r83 blue
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout nineteenth_branch
cp -r ../commits/commit83/. ./src
git add .
git commit -m "blue did r83"
echo 'blue did r83'

# r84 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout eighth_branch
cp -r ../commits/commit84/. ./src
git add .
git commit -m 'red did r84'
echo 'red did r84'

# r85 red
git checkout eleventh_branch
if git merge eighth_branch --no-commit;then
    echo "Смогли мерджик сделать 😝"
else
    echo "Не осилили слияние 🤬"
fi
cp -r ../commits/commit85/. ./src
git add .
git commit -m 'red did r85'
echo 'red did r85'

# r86 red
git checkout thirteenth_branch
cp -r ../commits/commit86/. ./src
git add .
git commit -m 'red did r86'
echo 'red did r86'

# r87 blue DO MERGE 🥹
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
git checkout fifth_branch
if git merge thirteenth_branch --no-commit; then
	echo 'Смогли мержик сделать😝'
else
	echo 'Не осилили слияние 🤬'
fi
cp -r ../commits/commit87/. ./src
git add .
git commit -m "blue did r87"
echo 'blue did r87'

# r88 blue
git checkout nineteenth_branch
cp -r ../commits/commit88/. ./src
git add .
git commit -m "blue did r88"
echo 'blue did r 88'

# r89 blue DO MERGE 🥹
git checkout ninth_branch
if git merge nineteenth_branch --no-commit; then
	echo 'забабахали мерж😝'
else
	echo 'потеряли мерж 🤬'
fi
cp -r ../commits/commit89/. ./src
git add .
git commit -m "blue did r89"
echo 'blue did r89'

# r90 blue DO MERGE 🥹
git checkout fourth_branch
if git merge ninth_branch --no-commit;then
	echo 'ой ой мержик на месте 😝'
else
	echo 'мержа не будет 🤬'
fi
cp -r ../commits/commit90/. ./src
git add .
git commit -m "blue did r90"
echo 'blue did r90'

# r91 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout eleventh_branch
if git merge third_branch --no-commit;then
    echo "Смогли мерджик сделать 😝"
else
    echo "Не осилили слияние 🤬"
fi
cp -r ../commits/commit91/. ./src
git add .
git commit -m 'red did r91'
echo 'red did r91'

# r92 red
cp -r ../commits/commit92/. ./src
git add .
git commit -m 'red did r92'
echo 'red did r92'

# r93 blue DO MERGE 🥹
git config --global user.name "blue"
git config --global user.mail "blue@jupiter.ru"
if git merge eleventh_branch --no-commit;then
	echo 'смогли мержик сделать 😝'
else
	echo 'не осилили слияние 🤬'
fi
cp -r ../commits/commit93/. ./src
git add .
git commit -m "blue did r93"
echo 'blue did r93'

# r94 blue
cp -r ../commits/commit94/. ./src
git add .
git commit -m "blue did r94"
echo 'blue did r94'

# r95 red
git config --global user.name "red"
git config --global user.mail "red@itmo.ru"
git checkout first_branch
if git merge fifth_branch --no-commit;then
    echo "Смогли мерджик сделать 😝"
else
    echo "Не осилили слияние 🤬"
fi
cp -r ../commits/commit95/. ./src
git add .
git commit -m 'red did r95'
echo 'red did r95'



git log --graph
