from my_dop3 import private


def xml_extract(xml_file) -> str:
    if '.xml' not in xml_file:
        xml_file += '.xml'
    with open(xml_file, encoding='utf-8') as f:
        file = f.read()
    return file


def write_in_file(file_content, filename) -> None:
    with open(filename, 'w', encoding='utf-8') as f:
        f.write(file_content)
    return


def single_string_xml(in_filename, out_filename) -> None:
    if private.private_is_valid(xml_extract(in_filename)):
        write_in_file(private.private_single_string_xml(xml_extract(in_filename)), out_filename)
    else:
        print("xml syntax error")


def beautify(in_filename, out_filename) -> None:
    if private.private_is_valid(xml_extract(in_filename)):
        write_in_file(private.private_beautify(xml_extract(in_filename)), out_filename)
    else:
        print("xml syntax error")


def is_valid(in_filename) -> bool:
    return private.private_is_valid(xml_extract(in_filename))


def parser(in_filename, out_filename) -> None:
    if private.private_is_valid(xml_extract(in_filename)):
        write_in_file(private.private_parser(xml_extract(in_filename)), out_filename)
    else:
        print('xml syntax error')

