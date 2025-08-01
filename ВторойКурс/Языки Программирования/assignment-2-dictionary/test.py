import subprocess
import unittest

class TestDictionary(unittest.TestCase):
	def run_program(self, input_data):
        	path = './main'
        	result = subprocess.run([path], input=input_data, text=True, capture_output=True)
        	return result.stdout, result.stderr

	def test_alcohol(self):
        	input_data = 'alcohol'
        	expected_out = 'not good'
        	expected_err = ""
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err)
	
	
	def test_cigaretes(self):
        	input_data = 'cigaretes'
        	expected_out = 'not cool'
        	expected_err = ""
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err)        
    	
    	
	def test_vapes(self):
        	input_data = 'vapes are great?'
        	expected_out = 'they are unhealthy'
        	expected_err = ""
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err)
        	
	
	def test_nagging(self):
        	input_data = 'PSZH'
        	expected_out = 'very_loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong_nagging'
        	expected_err = ""
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err)        	 
        	   


	def test_error(self):
        	input_data = "LOOOOL"
        	expected_out = ''
        	expected_err = "Didnt find any key"
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err) 
        	
	def test_overflow(self):
        	input_data = "LOOOOL" * 45
        	expected_out = ''
        	expected_err = "Overflow!"
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err)    
        	
	def test_newline(self):
        	input_data = '\n' 
        	expected_out = 'this is a newline key'
        	expected_err = ""
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err) 


	def test_empty(self):
        	input_data = ' ' 
        	expected_out = 'this is an empty key'
        	expected_err = ""
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err)         		     	
        	       	 
        	       	 
	def test_tabulation(self):
        	input_data = '\t' 
        	expected_out = 'this is a tabulation key'
        	expected_err = ""
        	output, error = self.run_program(input_data)
        	self.assertEqual(output.strip(), expected_out)
        	self.assertEqual(error.strip(), expected_err)        	       	 
        	        




    

    
if __name__ == '__main__':
    unittest.main()

