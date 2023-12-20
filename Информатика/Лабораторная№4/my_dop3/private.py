def private_single_string_xml(file_content) -> str:
    file_content = '\n' + file_content
    while '\n ' in file_content:
        file_content = file_content.replace('\n ', '\n')
    while ' >' in file_content:
        file_content = file_content.replace(' >', '>')
    while '> ' in file_content:
        file_content = file_content.replace('> ', '>')
    return file_content.replace('\n', '')


def private_beautify(file_content) -> str:
    file_content = private_single_string_xml(file_content).replace('><', '>\n<')
    file_content = file_content.split('\n')
    f = 0
    for i, string in enumerate(file_content):
        if not i:
            continue
        file_content[i] = ' ' * 2 * f + string
        f += string.count('<') - 2 * string.count('</') - string.count('<?')
    for i, string in enumerate(file_content):
        if '</' in string:
            tag = string[string.find('</'): string.rfind('>') + 1].replace('/', '').replace(' ', '')
            # print(tag) #eu
            if tag not in string:
                file_content[i] = string.replace('  </', '</')
    return '\n'.join(file_content)


def private_is_valid(file_content) -> bool:
    file_content = private_single_string_xml(file_content).split('?>')[-1]
    # print('file_content: ', file_content) # debu
    if '< ' in file_content or '</ ' in file_content:
        return False
    file_content = file_content.split('<')[1:]
    tags = []
    for v in file_content:
        tags.append('<' + v[:v.find('>') + 1])
        # print('tag: ', '<' + v[:v.find('>') + 1]) # deug
    while len(tags):
        t = 0
        for i in range(len(tags) - 1):
            if tags[i] == tags[i + 1].replace('</', '<'):
                tags.pop(i)
                tags.pop(i)
                #print(tags)
                t = 1
                break
        if not t: return False
    return True


def private_parser(file_content) -> str:
    if not private_is_valid(file_content):
        print('Invalid .xml syntax')
        return ''
    else:
        file_content = private_beautify(private_single_string_xml(file_content)).split('\n')
        converted_file_content = []
        for string in file_content[1:]:
            converted_file_content.append(string.split('</')[0].replace('<', '').replace('>', ': '))
        return ('\n'.join(converted_file_content)).replace('\n\n', '\n')

