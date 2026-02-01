RepoPath="file://$(pwd)/Repository2005"

svnadmin create "$(pwd)/Repository2005"
svn mkdir $RepoPath/branches $RepoPath/trunk -m "added dirs"
svn checkout $RepoPath/trunk working_copy
cd working_copy



# r0 red
svn copy $RepoPath/trunk $RepoPath/branches/first_branch -m "Created first_branch"
svn switch $RepoPath/branches/first_branch
cp -r ../commits/commit0/. ./
svn add *
svn commit -m "red did r0" --username "red"


# r1 blue
svn copy $RepoPath/branches/first_branch $RepoPath/branches/seventeenth_branch -m "Created seventeenth_branch"
svn switch $RepoPath/branches/seventeenth_branch
cp -r ../commits/commit1/. ./
svn add *
svn commit -m "blue did r1" --username "blue" --force-log



# r2 red
svn copy $RepoPath/branches/seventeenth_branch $RepoPath/branches/twentieth_branch -m "Created twentieth_branch"
svn switch $RepoPath/branches/twentieth_branch
svn rm *
cp -r ../commits/commit2/. ./
svn add *
svn commit -m "red did r2" --username "red"

# r3 red
svn copy $RepoPath/branches/twentieth_branch $RepoPath/branches/thirteenth_branch -m "Created thirteenth_branch"
svn switch $RepoPath/branches/thirteenth_branch
svn rm *
cp -r ../commits/commit3/. ./
svn add *
svn commit -m "red did r3" --username "red"


# r4 blue
svn copy $RepoPath/branches/thirteenth_branch $RepoPath/branches/third_branch -m "Created third_branch"
svn switch $RepoPath/branches/third_branch
svn rm *
cp -r ../commits/commit4/. ./
svn add *
svn commit -m "blue did r4" --username "blue"

# r5 blue
svn copy $RepoPath/branches/third_branch $RepoPath/branches/fourteenth_branch -m "Created fourteenth_branch"
svn switch $RepoPath/branches/fourteenth_branch
svn rm *
cp -r ../commits/commit5/. ./
svn add *
svn commit -m "blue did r5" --username "blue"

# r6 blue
svn rm *
cp -r ../commits/commit6/. ./
svn add *
svn commit -m "blue did r6" --username "blue"

# r7 red
svn switch $RepoPath/branches/first_branch
svn rm *
cp -r ../commits/commit7/. ./
svn add *
svn commit -m "red did r7" --username "red"

# r8 blue
svn copy $RepoPath/branches/first_branch $RepoPath/branches/eighteenth_branch -m "Created eighteenth_branch"
svn switch $RepoPath/branches/eighteenth_branch
svn rm *
cp -r ../commits/commit8/. ./
svn add *
svn commit -m "blue did r8" --username "blue"

# r9 red
svn switch $RepoPath/branches/thirteenth_branch
svn rm *
cp -r ../commits/commit9/. ./
svn add *
svn commit -m "red did r9" --username "red"

# r10 blue
svn copy $RepoPath/branches/thirteenth_branch $RepoPath/branches/fifth_branch -m "Created fifth_branch"
svn switch $RepoPath/branches/fifth_branch
svn rm *
cp -r ../commits/commit10/. ./
svn add *
svn commit -m "blue did r10" --username "blue"

# r11 red
svn switch $RepoPath/branches/thirteenth_branch
svn rm *
cp -r ../commits/commit11/. ./
svn add *
svn commit -m "red did r11" --username "red"

# r12 blue
svn copy $RepoPath/branches/thirteenth_branch $RepoPath/branches/second_branch -m "Created second_branch"
svn switch $RepoPath/branches/second_branch
svn rm *
cp -r ../commits/commit12/. ./
svn add *
svn commit -m "blue did r12" --username "blue"

# r13 red
svn switch $RepoPath/branches/thirteenth_branch
svn rm *
cp -r ../commits/commit13/. ./
svn add *
svn commit -m "red did r13" --username "red"

# r14 red
svn switch $RepoPath/branches/first_branch
svn rm *
cp -r ../commits/commit14/. ./
svn add *
svn commit -m "red did r14" --username "red"

# r15 blue
svn copy $RepoPath/branches/first_branch $RepoPath/branches/fourth_branch -m "Created fourth_branch"
svn switch $RepoPath/branches/fourth_branch
svn rm *
cp -r ../commits/commit15/. ./
svn add *
svn commit -m "blue did r15" --username "blue"

# r16 blue
svn copy $RepoPath/branches/fourth_branch $RepoPath/branches/ninth_branch -m "Created ninth_branch"
svn switch $RepoPath/branches/ninth_branch
svn rm *
cp -r ../commits/commit16/. ./
svn add  *
svn commit -m "blue did r16" --username "blue"



# r17 red
echo "meow"
svn copy $RepoPath/branches/ninth_branch $RepoPath/branches/fifteenth_branch -m "Created fifteenth_branch"
svn switch $RepoPath/branches/fifteenth_branch
svn rm *
cp -r ../commits/commit17/. ./
svn add * --force
svn commit -m "red did r17" --username "red"

# r18 blue
svn copy $RepoPath/branches/fifteenth_branch $RepoPath/branches/tenth_branch -m "Created tenth_branch"
svn switch $RepoPath/branches/tenth_branch
svn rm *
cp -r ../commits/commit18/. ./
svn add *
svn commit -m "blue did r18" --username "blue"

# r19 blue
svn switch $RepoPath/branches/ninth_branch
svn rm *
cp -r ../commits/commit19/. ./
svn add *
svn commit -m "blue did r19" --username "blue"

# r20 blue
svn switch $RepoPath/branches/eighteenth_branch
svn rm *
cp -r ../commits/commit20/. ./
svn add *
svn commit -m "blue did r20" --username "blue"

# r21 blue
svn copy $RepoPath/branches/eighteenth_branch $RepoPath/branches/sixth_branch -m "Created sixth_branch"
svn switch $RepoPath/branches/sixth_branch
svn rm *
cp -r ../commits/commit21/. ./
svn add *
svn commit -m "blue did r21" --username "blue"

# r22 blue
svn rm *
cp -r ../commits/commit22/. ./
svn add *
svn commit -m "blue did r22" --username "blue"

# r23 blue
svn switch $RepoPath/branches/seventeenth_branch
svn rm *
cp -r ../commits/commit23/. ./
svn add *
svn commit -m "blue did r23" --username "blue"

# r24 blue
svn switch $RepoPath/branches/tenth_branch
svn rm *
cp -r ../commits/commit24/. ./
svn add *
svn commit -m "blue did r24" --username "blue"

# r25 red
svn switch $RepoPath/branches/thirteenth_branch
svn rm *
cp -r ../commits/commit25/. ./
svn add *
svn commit -m "red did r25" --username "red"

# r26 red
svn copy $RepoPath/branches/thirteenth_branch $RepoPath/branches/seventh_branch -m "Created seventh_branch"
svn switch $RepoPath/branches/seventh_branch
svn rm *
cp -r ../commits/commit26/. ./
svn add *
svn commit -m "red did r26" --username "red"

# r27 blue
svn switch $RepoPath/branches/fourteenth_branch
svn rm *
cp -r ../commits/commit27/. ./
svn add *
svn commit -m "blue did r27" --username "blue"

# r28 red
svn copy $RepoPath/branches/fourteenth_branch $RepoPath/branches/eleventh_branch -m "Created eleventh_branch"
svn switch $RepoPath/branches/eleventh_branch
svn rm *
cp -r ../commits/commit28/. ./
svn add *
svn commit -m "red did r28" --username "red"

# r29
svn switch $RepoPath/branches/twentieth_branch
svn rm *
cp -r ../commits/commit29/. ./
svn add *
svn commit -m "red did r29" --username "red"

# r30 red
svn switch $RepoPath/branches/eleventh_branch
svn rm *
cp -r ../commits/commit30/. ./
svn add *
svn commit -m "red did r30" --username "red"

# r31 blue
svn switch $RepoPath/branches/second_branch
svn rm *
cp -r ../commits/commit31/. ./
svn add *
svn commit -m "blue did r31" --username "blue"

# r32 blue
svn switch $RepoPath/branches/sixth_branch
svn rm *
cp -r ../commits/commit32/. ./
svn add *
svn commit -m "blue did r32" --username "blue"

# r33 blue
svn rm *
cp -r ../commits/commit33/. ./
svn add *
svn commit -m "blue did r33" --username "blue"

# r34 blue
svn copy $RepoPath/branches/sixth_branch $RepoPath/branches/twelfth_branch -m "Created twelfth_branch"
svn switch $RepoPath/branches/twelfth_branch
svn rm *
cp -r ../commits/commit34/. ./
svn add *
svn commit -m "blue did r34" --username "blue"

# r35 red
svn switch $RepoPath/branches/twentieth_branch
svn rm *
cp -r ../commits/commit35/. ./
svn add *
svn commit -m "red did r35" --username "red"

# r36 red
svn switch $RepoPath/branches/fifteenth_branch
svn rm *
cp -r ../commits/commit36/. ./
svn add *
svn commit -m "red did r36" --username "red"

# r37 red
svn switch $RepoPath/branches/first_branch
svn rm *
cp -r ../commits/commit37/. ./
svn add *
svn commit -m "red did r37" --username "red"

# r38 red
svn switch $RepoPath/branches/twentieth_branch
svn rm *
cp -r ../commits/commit38/. ./
svn add *
svn commit -m "red did r38" --username "red"

# r39 blue
svn switch $RepoPath/branches/fifth_branch
svn rm *
cp -r ../commits/commit39/. ./
svn add *
svn commit -m "blue did r39" --username "blue"

# r40 red
svn switch $RepoPath/branches/eleventh_branch
svn rm *
cp -r ../commits/commit40/. ./
svn add *
svn commit -m "red did r40" --username "red"

# r41 blue
svn switch $RepoPath/branches/sixth_branch
svn rm *
cp -r ../commits/commit41/. ./
svn add *
svn commit -m "blue did r41" --username "blue"

# r42 blue
svn rm *
cp -r ../commits/commit42/. ./
svn add *
svn commit -m "blue did r42" --username "blue"

# r43 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fourth_branch
svn merge $RepoPath/branches/sixth_branch --accept working
svn resolve --accept working -R .
cp -r ../commits/commit43/. ./
svn add --force *

svn commit -m "blue did r43" --username "blue"

# r44 blue
svn switch $RepoPath/branches/fourteenth_branch
svn rm *
cp -r ../commits/commit44/. ./
svn add *
svn commit -m "blue did r44" --username "blue"

# r45 red
svn switch $RepoPath/branches/seventh_branch
svn rm *
cp -r ../commits/commit45/. ./
svn add *
svn commit -m "red did r45" --username "red"

# r46 blue
svn switch $RepoPath/branches/twelfth_branch
svn rm *
cp -r ../commits/commit46/. ./
svn add *
svn commit -m "blue did r46" --username "blue"

# r47 blue DO MERGE 🥹
svn switch  $RepoPath/branches/tenth_branch
svn merge $RepoPath/branches/twelfth_branch --accept working
cp -r ../commits/commit47/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r47" --username "blue"

# r48 red
svn switch $RepoPath/branches/seventh_branch
svn rm *
cp -r ../commits/commit48/. ./
svn add *
svn commit -m "red did r48" --username "red"

# r49 red
svn switch $RepoPath/branches/twentieth_branch
svn rm *
cp -r ../commits/commit49/. ./
svn add *
svn commit -m "red did r49" --username "red"

# r50 blue
svn switch $RepoPath/branches/fourteenth_branch
svn rm *
cp -r ../commits/commit50/. ./
svn add *
svn commit -m "blue did r50" --username "blue"

# r51 red
svn switch $RepoPath/branches/twentieth_branch
svn rm *
cp -r ../commits/commit51/. ./
svn add *
svn commit -m "red did r51" --username "red"

# r52 blue
svn switch $RepoPath/branches/seventeenth_branch
svn rm *
cp -r ../commits/commit52/. ./
svn add *
svn commit -m "blue did r52" --username "blue"

# r53 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fourteenth_branch
svn merge $RepoPath/branches/seventeenth_branch --accept working
cp -r ../commits/commit53/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r53" --username "blue"

# r54 red
svn switch $RepoPath/branches/eleventh_branch
svn rm *
cp -r ../commits/commit54/. ./
svn add *
svn commit -m "red did r54" --username "red"

# r55 red
svn switch $RepoPath/branches/fifteenth_branch
svn rm *
cp -r ../commits/commit55/. ./
svn add *
svn commit -m "red did r55" --username "red"

# r56 blue
svn copy $RepoPath/branches/fifteenth_branch $RepoPath/branches/nineteenth_branch -m "Created nineteenth_branch"
svn switch $RepoPath/branches/nineteenth_branch
svn rm *
cp -r ../commits/commit56/. ./
svn add *
svn commit -m "blue did r56" --username "blue"

# r57 red
svn switch $RepoPath/branches/twentieth_branch
svn rm *
cp -r ../commits/commit57/. ./
svn add *
svn commit -m "red did r57" --username "red"

# r58 blue DO MERGE 🥹
svn switch  $RepoPath/branches/tenth_branch
svn merge $RepoPath/branches/twentieth_branch --accept working
cp -r ../commits/commit58/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r58" --username "blue"

# r59 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fourteenth_branch
svn merge $RepoPath/branches/tenth_branchs --accept working
cp -r ../commits/commit59/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r59" --username "blue"

# r60 blue
svn switch $RepoPath/branches/fourth_branch
svn rm *
cp -r ../commits/commit60/. ./
svn add *
svn commit -m "blue did r60" --username "blue"

# r61 blue
svn rm *
cp -r ../commits/commit61/. ./
svn add *
svn commit -m "blue did r61" --username "blue"

# r62 red
svn switch $RepoPath/branches/fifteenth_branch
svn rm *
cp -r ../commits/commit62/. ./
svn add *
svn commit -m "red did r62" --username "red"

# r63 red
svn copy $RepoPath/branches/fifteenth_branch $RepoPath/branches/sixteenth_branch -m "Created sixteenth_branch"
svn switch $RepoPath/branches/sixteenth_branch
svn rm *
cp -r ../commits/commit63/. ./
svn add *
svn commit -m "red did r63" --username "red"
svn merge $RepoPath/branches/fifteenth_branch --accept working
svn add --force *
svn resolve --accept working -R .
svn commit -m "red did merge in 63" --username "red"

# r64 blue
svn switch $RepoPath/branches/eighteenth_branch
svn rm *
cp -r ../commits/commit64/. ./
svn add *
svn commit -m "blue did r64" --username "blue"

# r65 red
svn switch $RepoPath/branches/seventh_branch
svn rm *
cp -r ../commits/commit65/. ./
svn add *
svn commit -m "red did r65" --username "red"

# r66 blue
svn switch $RepoPath/branches/fifth_branch
svn rm *
cp -r ../commits/commit66/. ./
svn add *
svn commit -m "blue did r66" --username "blue"

# r67 red
svn switch $RepoPath/branches/first_branch
svn rm *
cp -r ../commits/commit67/. ./
svn add *
svn commit -m "red did r67" --username "red"

# r68 blue
svn switch $RepoPath/branches/fourteenth_branch
svn rm *
cp -r ../commits/commit68/. ./
svn add *
svn commit -m "blue did r68" --username "blue"

# r69 blue DO MERGE 🥹
svn switch  $RepoPath/branches/eighteenth_branch
svn merge $RepoPath/branches/fourteenth_branch --accept working
cp -r ../commits/commit69/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r69" --username "blue"


# r70 red
svn switch $RepoPath/branches/thirteenth_branch
svn rm *
cp -r ../commits/commit70/. ./
svn add *
svn commit -m "red did r70" --username "red"

# r71 red
svn switch $RepoPath/branches/seventh_branch
svn rm *
cp -r ../commits/commit71/. ./
svn add *
svn commit -m "red did r71" --username "red"

# r72 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fifth_branch
svn merge $RepoPath/branches/seventh_branch --accept working
cp -r ../commits/commit72/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r72" --username "blue"

# r73 blue
svn switch $RepoPath/branches/fourth_branch
svn rm *
cp -r ../commits/commit73/. ./
svn add *
svn commit -m "blue did r73" --username "blue"

# r74 red
svn switch $RepoPath/branches/first_branch
svn rm *
cp -r ../commits/commit74/. ./
svn add *
svn commit -m "red did r74" --username "red"

# r75 blue
svn switch $RepoPath/branches/third_branch
svn rm *
cp -r ../commits/commit75/. ./
svn add *
svn commit -m "blue did r75" --username "blue"

# r76 red
svn switch  $RepoPath/branches/thirteenth_branch
svn merge $RepoPath/branches/third_branch --accept working
cp -r ../commits/commit76/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "red did r76" --username "red"


# r77 blue
svn switch $RepoPath/branches/seventeenth_branch
svn rm *
cp -r ../commits/commit77/. ./
svn add *
svn commit -m "blue did r77" --username "blue"

# r78 blue
svn switch $RepoPath/branches/second_branch
svn rm *
cp -r ../commits/commit78/. ./
svn add *
svn commit -m "blue did r78" --username "blue"

# r79 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fifteenth_branch
svn merge $RepoPath/branches/second_branch --accept working
cp -r ../commits/commit79/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r79" --username "blue"

# r80 blue DO MERGE 🥹
svn switch  $RepoPath/branches/eighteenth_branch
svn merge $RepoPath/branches/fifteenth_branch --accept working
cp -r ../commits/commit80/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r80" --username "blue"

# r81 red
svn switch  $RepoPath/branches/first_branch
svn merge $RepoPath/branches/third_branch --accept working
cp -r ../commits/commit81/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "red did r81" --username "red"

# r82 red
svn copy $RepoPath/branches/first_branch $RepoPath/branches/eighth_branch -m "Created eighth_branch"
svn switch $RepoPath/branches/eighth_branch
svn rm *
cp -r ../commits/commit82/. ./
svn add *
svn commit -m "red did r82" --username "red"

# r83 blue
svn switch $RepoPath/branches/nineteenth_branch
svn rm *
cp -r ../commits/commit83/. ./
svn add *
svn commit -m "blue did r83" --username "blue"

# r84 red
svn switch $RepoPath/branches/eighth_branch
svn rm *
cp -r ../commits/commit84/. ./
svn add *
svn commit -m "red did r84" --username "red"

# r85 red
svn switch  $RepoPath/branches/eleventh_branch
svn merge $RepoPath/branches/eighth_branch --accept working
cp -r ../commits/commit85/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "red did r85" --username "red"

# r86
svn switch $RepoPath/branches/thirteenth_branch
svn rm *
cp -r ../commits/commit86/. ./
svn add *
svn commit -m "red did r86" --username "red"

# r87 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fifth_branch
svn merge $RepoPath/branches/thirteenth_branch --accept working
cp -r ../commits/commit87/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r87" --username "blue"

# r88 blue
svn switch $RepoPath/branches/nineteenth_branch
svn rm *
cp -r ../commits/commit88/. ./
svn add *
svn commit -m "blue did r88" --username "blue"

# r89 blue DO MERGE 🥹
svn switch  $RepoPath/branches/ninth_branch
svn merge $RepoPath/branches/nineteenth_branch --accept working
cp -r ../commits/commit89/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r89" --username "blue"

# r90 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fourth_branch
svn merge $RepoPath/branches/ninth_branch --accept working
cp -r ../commits/commit90/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r90" --username "blue"

# r91 red
svn switch  $RepoPath/branches/eleventh_branch
svn merge $RepoPath/branches/third_branch --accept working
cp -r ../commits/commit91/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "red did r91" --username "red"

# r92 red
svn rm *
cp -r ../commits/commit92/. ./
svn add *
svn commit -m "red did r92" --username "red"

# r93 blue DO MERGE 🥹
svn switch  $RepoPath/branches/fifth_branch
svn merge $RepoPath/branches/eleventh_branch --accept working
cp -r ../commits/commit93/. ./
svn add --force *
svn resolve --accept working -R .
svn commit -m "blue did r93" --username "blue"

# r94 blue
svn switch $RepoPath/branches/nineteenth_branch
svn rm *
cp -r ../commits/commit94/. ./
svn add *
svn commit -m "blue did r94" --username "blue"

# r95
svn switch  $RepoPath/branches/first_branch
svn merge $RepoPath/branches/fifth_branch --accept working
cp -r ../commits/commit95/. ./
svn add --force *
svn resolve --action postpone
svn commit -m "red did r95" --username "red"
